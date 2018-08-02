package com.cg.mypaymentapp.beans;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="Transactions")
public class Transactions {	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String mobileNo;
	
	private String statements;
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatements() {
		return statements;
	}
	public void setStatements(String statements) {
		this.statements = statements;
	}
	
}
