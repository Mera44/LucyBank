package com.lucy.domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class CreditAccount extends Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1599411897265986285L;
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
