package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{	
	
	public Customer findByProfileUserName(String userName);
}
