package com.cg.parallelproject.beans;

public class Customer {
	
	private String custName;
	private String mobileNo;
	private Wallet wallet;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Customer(String custName, Wallet wallet) {
		super();
		this.custName = custName;
		this.wallet = wallet;
	}


	public Customer(String name2, String mobileNo2, Wallet wallet2) {
		this.custName = name2;
		this.mobileNo = mobileNo2;
		this.wallet = wallet2;
	}
	public String getName() {
		return custName;
	}
	public void setName(String name) {
		this.custName = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}


	@Override
	public String toString() {
		return "Customer [name=" + custName + ", mobileNo=" + mobileNo + ", wallet=" + wallet + "]";
	}
	

}
