package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.SavingAccount;

@Repository
public interface SavingAccountRepository extends CrudRepository<SavingAccount, Long>{
	public SavingAccount findByAccountNumber(Integer accNo);
}
