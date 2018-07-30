package com.cg.parallelproject.service;

import java.math.BigDecimal;

import com.cg.parallelproject.beans.Customer;
import com.cg.parallelproject.exception.InsufficientBalanceException;
import com.cg.parallelproject.exception.InvalidInputException;


public interface WalletService {

	public Customer createAccount(String name ,String mobileno, BigDecimal amount) throws InvalidInputException;
	public Customer showBalance (String mobileno) throws InvalidInputException;
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
	public Customer depositAmount (String mobileNo,BigDecimal amount ) throws InvalidInputException;
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, InsufficientBalanceException;
    public boolean isValidNo(String mobileNo) throws InvalidInputException;
    public boolean isValidName(String name) throws InvalidInputException;
}
