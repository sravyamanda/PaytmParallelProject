package com.cg.parallelproject.repo;

import java.util.HashMap;
import java.util.Map;

import com.cg.parallelproject.beans.Customer;
import com.cg.parallelproject.exception.IException;
import com.cg.parallelproject.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo {

private Map<String, Customer> data; 
	
	public WalletRepoImpl() 
	{
		data = new HashMap<String, Customer>();
	}

	public boolean save(Customer customer) 
	{
		if(data.get(customer.getMobileNo()) == null) {
			data.put(customer.getMobileNo(), customer);
			return true;
		}
		return false;
	}

	public void remove(String mobileNo) {
		data.remove(mobileNo);
	}
	public Customer findOne(String mobileNo) throws InvalidInputException {
		if(data.get(mobileNo) != null) {
			return data.get(mobileNo);
		}
		throw new InvalidInputException(IException.Message4);
	}
}
