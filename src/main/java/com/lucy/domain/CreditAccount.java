package com.lucy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class CreditAccount extends Account {
	
	//@Column
	private  double interestRate = 0.2;

	public double getInterestRate() {
		return interestRate;
	}
	
}
