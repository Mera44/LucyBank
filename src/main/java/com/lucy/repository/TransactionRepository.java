package com.lucy.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
	public Transaction findByTransactionDate(Date date);
	public Transaction findByTransactionType(TransactionType type);
//	@Query("select t from Transaction t where t.transactionDate between :startDate and :endDate and t.acc_id = :accId")
//	public List<Transaction> getByAccountNumberAndDates(@Param("accId") long accId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}