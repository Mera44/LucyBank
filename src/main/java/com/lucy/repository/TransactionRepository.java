package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;

import com.lucy.domain.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{

}