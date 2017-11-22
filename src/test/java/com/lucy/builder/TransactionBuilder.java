package com.lucy.builder;

import java.sql.Date;

import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;

public class TransactionBuilder {
	private Transaction transaction;
	public TransactionBuilder() {
		this.transaction = new Transaction();
	}
	
	public TransactionBuilder withId(long id){
		this.transaction.setId(id);
		return this;
	}
	
	public TransactionBuilder withTransactionDate(Date date){
		this.transaction.setTransactionDate(date);
		return this;
	}
	
	public TransactionBuilder withTransactionAmount(Double amount){
		this.transaction.setTransactionAmount(amount);
		return this;
	}
	
	public TransactionBuilder withStartingBalance(Double startingBalance){
		this.transaction.setStartingBalance(startingBalance);
		return this;
	}
	
	public TransactionBuilder withEndingBalance(Double endingBalance){
		this.transaction.setEndingBalance(endingBalance);
		return this;
	}
	
	public TransactionBuilder withTransactionType(TransactionType type){
		this.transaction.setTransactionType(type);
		return this;
	}
	
	public Transaction build(){
		return this.transaction;
	}
}
