package com.cg.parallelproject.service;

import java.math.BigDecimal;

import com.cg.parallelproject.beans.Customer;
import com.cg.parallelproject.beans.Wallet;
import com.cg.parallelproject.exception.IException;
import com.cg.parallelproject.exception.InsufficientBalanceException;
import com.cg.parallelproject.exception.InvalidInputException;
import com.cg.parallelproject.repo.WalletRepo;
import com.cg.parallelproject.repo.WalletRepoImpl;


public class WalletServiceImpl implements WalletService {

	private WalletRepo repo;

	public WalletServiceImpl() {
		repo = new WalletRepoImpl();
	}

	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws InvalidInputException {
		if(isValidNo(mobileNo) && isValidName(name)) {
			Wallet wallet = new Wallet();
			Customer customer = new Customer();
		
			wallet.setBalance(amount);
			customer.setName(name);
			customer.setMobileNo(mobileNo);
			customer.setWallet(wallet);
		
			repo.save(customer);

			return customer;
		}
		else throw new InvalidInputException();

	}

	public Customer showBalance(String mobileNo) throws InvalidInputException {
		Customer customer=repo.findOne(mobileNo);
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException(IException.Message5);
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException {
		if(isValidNo(sourceMobileNo) == false || isValidNo(targetMobileNo) == false) throw new InvalidInputException();
		Customer customer = withdrawAmount(sourceMobileNo, amount);
		depositAmount(targetMobileNo, amount);
		return customer;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException 
	{
		if(isValidNo(mobileNo)) 
		{
			Customer customer = repo.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setBalance(wallet.getBalance().add(amount));
		
			repo.remove(mobileNo);
		
			if(repo.save(customer)) {
				return customer;
			}
		}
		return null;
	}

	public boolean isValidNo(String mobileNo) throws InvalidInputException {
		if(mobileNo.matches("[1-9][0-9]{9}")) 
		{
			return true;
		}		
		else 
		    throw new InvalidInputException(IException.Message2);
	}
	public boolean isValidName(String name) throws InvalidInputException {
		if(name.matches("[A-Za-z]*")) 
		{
			return true;
		}		
		else 
			throw new InvalidInputException(IException.Message1);
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException {
		if(isValidNo(mobileNo)) 
		{
			Customer customer = repo.findOne(mobileNo);
			Wallet wallet = customer.getWallet();
			wallet.setBalance(wallet.getBalance().subtract(amount));
		
			if(amount.compareTo(wallet.getBalance()) > 0) 
				throw new InsufficientBalanceException(IException.Message3);
		
			repo.remove(mobileNo);
		
			if(repo.save(customer)) {
				return customer;
			}
			return null;
		}
		else throw new InvalidInputException(IException.Message4);
	}
}
