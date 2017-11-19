package com.lucy.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Range;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name ="id", updatable = false, nullable = false)
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Transaction> transaction;
	@Range(min=10000, max=99999)
    private Integer accountNumber;
	@OneToOne
	private CardNumber cardNumber;
	protected Double balance = 0.0;
	private Boolean status = true;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public double getInterestRate() {
//		return interestRate;
//	}
//	public void setInterestRate(double interestRate) {
//		this.interestRate = interestRate;
//	}
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
