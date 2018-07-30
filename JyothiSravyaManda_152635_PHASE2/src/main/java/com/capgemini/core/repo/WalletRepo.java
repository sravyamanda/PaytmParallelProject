package com.capgemini.core.repo;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.exception.InvalidInputException;

public interface WalletRepo {

	public boolean save(Customer customer) throws InvalidInputException;

	public Customer findOne(String mobileNo) throws InvalidInputException;

	public void remove(String mobileNo);
}
