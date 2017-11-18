package com.lucy.serviceImpl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.repository.TransactionRepository;
import com.lucy.service.AccountService;
import com.lucy.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountService accountService;
	
	@Override
	public Transaction findById(long id) {		
		return transactionRepository.findOne(id);
	}

	@Override
	public List<Transaction> getAllTransaction() {		
		return (List<Transaction>) transactionRepository.findAll();
	}

	@Override
	public Transaction save(Transaction transaction) {		
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction update(Transaction transaction) {		
		return transactionRepository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) {		
		transactionRepository.delete(transaction);;
	}

	@Override
	public Transaction createTransaction(Integer accNo, double amount, String description, TransactionType type) {
		Transaction transaction = new Transaction();
		transaction.setTransactionType(type);
		transaction.setTransactionAmount(amount);
		transaction.setTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		//transaction.setDescription(description);
		//Account
		return transaction;
	}

	@Override
	public List<Transaction> getTransactionByUser(String username) {
		
		return null;
	}

	@Override
	public Transaction getByDate(Date date) {
		return transactionRepository.findByTransactionDate(date);
	}

	@Override
	public Transaction getByType(TransactionType type) {
		return transactionRepository.findByTransactionType(type);
	}

//	@Override
//	public List<Transaction> getByAccountNumberAndDates(long accId, Date startDate, Date endDate) {
//		return transactionRepository.getByAccountNumberAndDates(accId, startDate, endDate);
//	}

}
