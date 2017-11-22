package com.lucy.builder;

import java.util.List;

import com.lucy.domain.SavingAccount;
import com.lucy.domain.Transaction;



public class SavingAccountBuilder {

	private SavingAccount account;
  
	public SavingAccountBuilder() {
		this.account = new SavingAccount();
	}
	
    public SavingAccountBuilder withId(Long id) {
        this.account.setId(id);
        return this;
    }

    public SavingAccountBuilder withBalance(Double balance) {
        this.account.setBalance(balance);
        return this;
    }
    
 
    
    public SavingAccountBuilder withTransaction(List<Transaction> transaction) {
        this.account.setTransaction(transaction);
        return this;
    }
    
    public SavingAccountBuilder withStatus(Boolean status ) {
        this.account.setStatus(status);
        return this;
    }
    
    public SavingAccountBuilder withTypeAccount(String typeAccount ) {
        this.account.setTypeAccount(typeAccount);
        return this;
    }
    

      public SavingAccount build() {
        return account;
    }

	
}
