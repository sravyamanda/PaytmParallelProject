package com.cg.parallelproject.repo;

import com.cg.parallelproject.beans.Customer;
import com.cg.parallelproject.exception.InvalidInputException;

public interface WalletRepo {


	public boolean save(Customer customer);

	public Customer findOne(String mobileNo) throws InvalidInputException;

	public void remove(String mobileNo);
}
