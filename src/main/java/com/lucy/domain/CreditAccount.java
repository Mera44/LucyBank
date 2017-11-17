package com.lucy.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CreditAccount extends Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private  double interestRate = 0.2;

	public double getInterestRate() {
		return interestRate;
	}
	
}