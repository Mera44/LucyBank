package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Account;
import com.lucy.domain.CheckingAccount;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{
	public Account findByAccountNumber(Integer accNo);
	
}
