package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import com.lucy.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

}
