package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.CheckingAccount;

@Repository
public interface CheckingAccountRepository extends CrudRepository<CheckingAccount, Long>{
	public CheckingAccount findByAccountNumber(Integer accNo);
}
