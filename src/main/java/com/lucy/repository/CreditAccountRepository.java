package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;

import com.lucy.domain.CreditAccount;

public interface CreditAccountRepository extends CrudRepository<CreditAccount, Long>{
	public CreditAccount findByAccountNumber(Integer accNo);
}
