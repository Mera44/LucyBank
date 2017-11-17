package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{

}