package com.cg.parallelproject.pl;

	import java.math.BigDecimal;
	import java.util.Scanner;

import com.cg.parallelproject.beans.Customer;
import com.cg.parallelproject.beans.Wallet;
import com.cg.parallelproject.exception.IException;
import com.cg.parallelproject.exception.InsufficientBalanceException;
import com.cg.parallelproject.exception.InvalidInputException;
import com.cg.parallelproject.service.WalletService;
import com.cg.parallelproject.service.WalletServiceImpl;

	public class Client 
	{
		WalletService service;
		
		Client() {
			service = new WalletServiceImpl();
		}
		public void menu() {
			System.out.println("1) Create Account");
			System.out.println("2) Show Balance");
			System.out.println("3) Deposit Amount");
			System.out.println("4) Withdraw Amount");
			System.out.println("5) Fund Transfer");
			System.out.println("0) Exit Application");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					Customer customer = new Customer();
					Wallet wallet = new Wallet();
					
					System.out.print("Enter name: ");
					String name = sc.next();
					
					System.out.print("Enter mobileNumber: ");
					String mobileNumber = sc.next();
					
					System.out.print("Enter Amount: ");
					BigDecimal amount = sc.nextBigDecimal();
					
					try 
					{
						customer = service.createAccount(name, mobileNumber, amount);
						System.out.println("Your account has successfully registered");
					} 
					catch (InvalidInputException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 2:
					System.out.println("Enter mobile number");
					mobileNumber = sc.next();
					
				try {
					customer = service.showBalance(mobileNumber);
					System.out.print("The balance in account " + customer.getName());
					System.out.println(" is " + customer.getWallet().getBalance());
				     } catch (InvalidInputException e3) {
				    	 System.out.println(e3.getMessage());
				    }
					break;
				case 3:
					System.out.println("Enter mobile number");
					mobileNumber = sc.next();
					
					System.out.println("Enter amount to be deposited");
					amount = sc.nextBigDecimal();
					
					try 
					{
						customer = service.depositAmount(mobileNumber, amount);
						System.out.println("Successfully deposited");
						System.out.println("Account balance is: " + customer.getWallet().getBalance());
					} 
					catch (InvalidInputException e2) 
					{
						System.out.println(e2.getMessage());
					}
					break;
				case 4:
					System.out.println("Enter mobile number");
					mobileNumber = sc.next();
					
					System.out.println("Enter amount to be withdrawn");
					amount = sc.nextBigDecimal();
					
					try 
					{
						customer = service.withdrawAmount(mobileNumber, amount);
						System.out.println("Successfully withdrawn");
						System.out.println("Account balance is: " + customer.getWallet().getBalance());
					}
					catch (InvalidInputException e1) 
					{
						System.out.println(e1.getMessage());
					} catch (InsufficientBalanceException e) {
						System.out.println(e.getMessage());
					}
					break;
				
				case 5:
					System.out.print("Enter source mobile number: ");
					String sourceMobile = sc.next();
					
					System.out.print("Enter target mobile number: ");
					String targetMobile = sc.next();
					
					System.out.println("Enter amount to be transferred");
					amount = sc.nextBigDecimal();
					
					try 
					{
						customer = service.fundTransfer(sourceMobile, targetMobile, amount);
						System.out.println("Amount has successfully transferred from account " + customer.getName());
						System.out.println("And now your balance is " + customer.getWallet().getBalance());

					} 
					catch (InvalidInputException e) {
						System.out.println(e.getMessage());
					} catch (InsufficientBalanceException e) {
						System.out.println(e.getMessage());
					
					}
					break;
				case 0:
					System.out.println("Thank you for using our services");
					System.out.println("Good Bye");
					System.exit(0);
				
				default:
					System.out.println("Please enter valid choice");
					break;
					
					
			}
		
		}
		
		public static void main(String[] args) 
		{
			Client client = new Client();
			while(true) {
				client.menu();
			}
		}
		
		
	}



