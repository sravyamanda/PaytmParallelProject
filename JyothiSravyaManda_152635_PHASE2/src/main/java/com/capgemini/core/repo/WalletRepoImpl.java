package com.capgemini.core.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.beans.Wallet;
import com.capgemini.core.exception.InvalidInputException;
import com.capgemini.core.util.DBUtil;

public class WalletRepoImpl implements WalletRepo{

	@Override
	public boolean save(Customer customer) {
		try(Connection con = DBUtil.getConnection())
		{
			PreparedStatement pstm = con.prepareStatement("insert into customers1 values(?,?,?)");
			pstm.setString(1, customer.getName());
			pstm.setString(2, customer.getMobileNo());
			pstm.setBigDecimal(3, customer.getWallet().getBalance());
			
			pstm.execute();
			return true;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Customer findOne(String mobileNo) throws InvalidInputException {
		try(Connection con = DBUtil.getConnection())
		{
			PreparedStatement pstm = con.prepareStatement("select * from customers1 where MOBILENO = ?");
			pstm.setString(1, mobileNo);
			ResultSet res = pstm.executeQuery();
			
			if(res.next() == false) throw new InvalidInputException("No customers1 with this mobile Number");
			
			Customer customer = new Customer();
			Wallet wallet = new Wallet();
			
			customer.setName(res.getString(1));
			customer.setMobileNo(res.getString(2));
			
			wallet.setBalance(res.getBigDecimal(3));
			customer.setWallet(wallet);
			
			return customer;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void remove(String mobileNo) {
		try(Connection con = DBUtil.getConnection())
		{
			PreparedStatement pstm = con.prepareStatement("delete from customers1 where mobileno = ?");
			
			pstm.setString(1, mobileNo);
			pstm.execute();
			return;
			
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
