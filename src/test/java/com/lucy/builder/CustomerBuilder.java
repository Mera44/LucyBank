package com.lucy.builder;

import java.util.List;

import com.lucy.domain.Account;
import com.lucy.domain.Customer;
import com.lucy.domain.Profile;

public class CustomerBuilder {

	private Customer customer;
  
	public CustomerBuilder() {
		this.customer = new Customer();
	}
	
    public CustomerBuilder withAccountlistName(List<Account> accounts) {
        this.customer.setAccounts(accounts);
        return this;
    }

    public CustomerBuilder withId(Integer id) {
        this.customer.setId(id);
        return this;
    }
    
    public CustomerBuilder withProfile(Profile profile) {
        this.customer.setProfile(profile);
        return this;
    }
    

      public Customer build() {
        return customer;
    }

	
}
