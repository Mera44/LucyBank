package com.lucy.service;

import java.util.List;

import com.lucy.domain.Transaction;

public interface TransactionService {

	public Transaction findById(long id);
	public List<Transaction> getAllTransaction();
	public Transaction save(Transaction transaction);
	public Transaction update(Transaction transaction);
	public boolean delete(Transaction transaction);
	public Transaction createTransaction(Integer accNo, double amount, String description);
	public List<Transaction> getTransactionByUser(String username);
}
