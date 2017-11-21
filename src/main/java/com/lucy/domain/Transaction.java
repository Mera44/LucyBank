package com.lucy.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4757068825531283288L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private Date transactionDate;
	@NotNull
	@Min(0)
	private Double transactionAmount;
	private Double startingBalance;
	private Double endingBalance;
	private TransactionType transactionType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Double getStartingBalance() {
		return startingBalance;
	}
	public void setStartingBalance(Double startingBalance) {
		this.startingBalance = startingBalance;
	}
	public Double getEndingBalance() {
		return endingBalance;
	}
	public void setEndingBalance(Double endingBalance) {
		this.endingBalance = endingBalance;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	public Transaction setTransactionTypeFor(TransactionType transactionType) {
		this.transactionType = transactionType;
		return this;
	}

}
