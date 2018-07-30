package com.cg.parallelproject.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.parallelproject.beans.Customer;
import com.cg.parallelproject.beans.Wallet;
import com.cg.parallelproject.exception.InsufficientBalanceException;
import com.cg.parallelproject.exception.InvalidInputException;
import com.cg.parallelproject.service.WalletService;
import com.cg.parallelproject.service.WalletServiceImpl;

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
		String name = "bhanu";
		String number = "7095134616";
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
		String number = "7095134616";
		
		Customer customer = walletService.showBalance(number);
		
		assertNotSame(null, customer);
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testWithdrawAmount() throws InvalidInputException, InsufficientBalanceException {
		String name = "bhanu";
		String mobileNumber = "7095134616";
		BigDecimal balance = new BigDecimal(2000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}
	
	@Test
	public void testDepositAmount() throws InvalidInputException {
		String name = "bhanu";
		String mobileNumber = "7095134616";
		BigDecimal balance = new BigDecimal("3000");
		Boolean output = false;
		Customer customer = walletService.createAccount(name, mobileNumber, balance);
		Customer customer1 = walletService.depositAmount(mobileNumber, new BigDecimal("3000"));
		BigDecimal balanceOne = new BigDecimal(5000);
		if(balanceOne.compareTo(customer1.getWallet().getBalance())==0) {
			output = true;
		}
		assertTrue(output);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "bhanu";
		String mobileNumber = "70951346163";
		BigDecimal balance = new BigDecimal(3000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}

}
