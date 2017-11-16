package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;

import com.lucy.domain.SavingAccount;

public interface SavingAccountRepository extends CrudRepository<SavingAccount, Long>{
	public SavingAccount findByAccountNumber(Integer accNo);
}
