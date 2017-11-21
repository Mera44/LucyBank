package com.lucy.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SavingAccount extends Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7836887759855851030L;
	@Column
	private double interestRate = 0.05;

	public double getInterestRate() {
		return interestRate;
	}
	
	public SavingAccount setStatementBalance(){
		this.setBalance(getBalance()*(1.0+getInterestRate()));
		return this;
	}
}
