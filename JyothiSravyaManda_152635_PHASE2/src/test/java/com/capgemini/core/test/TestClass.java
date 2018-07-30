package com.capgemini.core.test;

import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.beans.Wallet;
import com.capgemini.core.exception.InsufficientBalanceException;
import com.capgemini.core.exception.InvalidInputException;
import com.capgemini.core.service.WalletService;
import com.capgemini.core.service.WalletServiceImpl;

public class TestClass {

	static WalletService walletService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		walletService = new WalletServiceImpl();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateAccount() throws InvalidInputException {
		Customer customer = new Customer();
		Wallet wallet = new Wallet();
		String name = "sravya";
		String number = "9790756040";
		BigDecimal amount = new BigDecimal(5000);
		
		customer.setName(name);
		customer.setMobileNo(number);
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		
		customer = walletService.createAccount(name, number, amount);
		
		assertNotSame(null, customer);
	}
	
	@Test
	public void testshowBalance() throws InvalidInputException {
		String number = "9790756040";
		
		Customer customer = walletService.showBalance(number);
		
		assertNotSame(null,customer);
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testWithdrawAmount() throws InvalidInputException, InsufficientBalanceException {
		String name = "sravya";
		String mobileNumber = "9790756040";
		BigDecimal balance = new BigDecimal("2000");
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal("3000");
		
		walletService.withdrawAmount(mobileNumber, amount);
	}
	
	@Test
	public void testDepositAmount() throws InvalidInputException {
		String name = "sravya";
		String mobileNumber = "9790756040";
		BigDecimal balance = new BigDecimal(3000);
		
		Customer customer = walletService.createAccount(name, mobileNumber, balance);
		System.out.println(customer.getWallet().getBalance());
		Customer customer2 = walletService.depositAmount(mobileNumber, new BigDecimal(3000));
		System.out.println(customer2.getWallet().getBalance());
		BigDecimal balanceOne=new BigDecimal(6000);
		assertNotSame(balanceOne , customer2.getWallet().getBalance());
		
	}
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "sravya";
		String mobileNumber = "9790756";
		BigDecimal balance = new BigDecimal("3000");
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal Amount = new BigDecimal("3000");
		
		walletService.withdrawAmount(mobileNumber, Amount);
	}


}
