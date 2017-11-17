package com.lucy.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Range;

@Entity
public abstract class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Transaction> transaction;
	@Range(min=10000, max=99999)
    	private Integer accountNumber;
	private CardNumber cardNumber;
	protected Double balance;
	private double interestRate;
	private Boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public CardNumber getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(CardNumber cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public boolean addTransaction(Transaction transaction) {
		return this.transaction.add(transaction);
	}

	
		

}
