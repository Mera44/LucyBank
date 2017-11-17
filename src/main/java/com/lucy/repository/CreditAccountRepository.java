package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.CreditAccount;

@Repository
public interface CreditAccountRepository extends CrudRepository<CreditAccount, Long>{
	public CreditAccount findByAccountNumber(Integer accNo);
}
