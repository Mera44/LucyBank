package com.lucy.domain;

import javax.persistence.Entity;

@Entity
public class CreditAccount extends Account {
	
	//@Column
	private  double interestRate = 0.2;
	private double creditLimit;

	public double getInterestRate() {
		return interestRate;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	
}
