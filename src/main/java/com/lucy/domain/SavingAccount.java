package com.lucy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SavingAccount extends Account {
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
