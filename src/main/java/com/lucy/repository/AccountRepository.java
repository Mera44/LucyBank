package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{
	public Account findByAccountNumber(Integer accNo);
	
}
