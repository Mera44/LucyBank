package com.lucy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class SavingAccount extends Account {
	@Column
	private double interestRate = 0.05;

	public double getInterestRate() {
		return interestRate;
	}

}
