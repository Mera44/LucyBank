package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import com.lucy.domain.CheckingAccount;

public interface CheckingAccountRepository extends CrudRepository<CheckingAccount, Long>{
	public CheckingAccount findByAccountNumber(Integer accNo);
}
