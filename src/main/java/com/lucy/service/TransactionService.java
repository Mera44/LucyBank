package com.lucy.service;

import java.sql.Date;
import java.util.List;

import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;

public interface TransactionService {

	public Transaction findById(long id);
	public List<Transaction> getAllTransaction();
	public Transaction save(Transaction transaction);
	public Transaction update(Transaction transaction);
	public void delete(Transaction transaction);
	public Transaction createTransaction(Integer accNo, double amount, String description,  TransactionType type);
	public List<Transaction> getTransactionByUser(String username);
	public Transaction getByDate(Date date);
	public Transaction getByType(TransactionType type);
	//public List<Transaction> getByAccountNumberAndDates(long accId,Date startDate, Date endDate);
}
