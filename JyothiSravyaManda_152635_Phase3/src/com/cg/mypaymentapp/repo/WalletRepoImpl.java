package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cg.mypaymentapp.JPAUtil.DButil;
import com.cg.mypaymentapp.JPAUtil.JPAUtil;
import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo{

	private EntityManager entityManager;
	
	public WalletRepoImpl()
	{
	super();
	entityManager =JPAUtil.getEntityManager();
	}
	
	public boolean save(Customer customer) throws InvalidInputException {
		
		String mob=customer.getMobileNo();
		String name=customer.getName();
		BigDecimal amount = customer.getWallet().getBalance();
		
		Customer cust=findOne(mob);
		
		if(cust!=null)
		{
			entityManager.getTransaction().begin();
			entityManager.merge(customer);
			entityManager.getTransaction().commit();
		}else
		{	
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			
			entityManager.getTransaction().commit();
		}
		return true;
	}

	public Customer findOne(String mobileNo)  {
		Customer cust=new Customer();
		entityManager.getTransaction().begin();
		
		cust = entityManager.find(Customer.class, mobileNo);	
		//System.out.println(cust);
		if(cust!=null)
		{
			entityManager.getTransaction().commit();
			return cust;
		}else {
			entityManager.getTransaction().commit();
			return null;
		}
			
	}


	@Override
	public  void saveTransaction(String mob,String trans) {
		Transactions tr=new Transactions();
		tr.setMobileNo(mob);
		tr.setStatements(trans);
		entityManager.getTransaction().begin();
		entityManager.persist(tr);
		entityManager.getTransaction().commit();
	}

	
	public List getTransaction(String mob)
	{
		entityManager.getTransaction().begin();
		
		Query query=entityManager.createQuery("select t.statements from Transactions t where t.mobileNo=?", String.class).setParameter(1, mob);   
		
		List<String> emplist = query.getResultList();
		
		entityManager.getTransaction().commit();
		return emplist;
	}
	
	
}
