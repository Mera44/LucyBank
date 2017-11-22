package com.lucy.builder;

import java.util.List;

import com.lucy.domain.CheckingAccount;
import com.lucy.domain.Transaction;



public class CheckingAccountBuilder {

	private CheckingAccount account;
  
	public CheckingAccountBuilder() {
		this.account = new CheckingAccount();
	}
	
    public CheckingAccountBuilder withId(Long id) {
        this.account.setId(id);
        return this;
    }

    public CheckingAccountBuilder withBalance(Double balance) {
        this.account.setBalance(balance);
        return this;
    }
    
 
    
    public CheckingAccountBuilder withTransaction(List<Transaction> transaction) {
        this.account.setTransaction(transaction);
        return this;
    }
    
    public CheckingAccountBuilder withStatus(Boolean status ) {
        this.account.setStatus(status);
        return this;
    }
    
    public CheckingAccountBuilder withTypeAccount(String typeAccount ) {
        this.account.setTypeAccount(typeAccount);
        return this;
    }
    

      public CheckingAccount build() {
        return account;
    }

	
}
