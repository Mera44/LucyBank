package com.lucy.serviceImpl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.lucy.domain.Statement;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;

@Component
public class StatementHelper {
	
	public Statement createSavingStatement(Statement statement, List<Transaction> allTransactions){
		return createCheckingStatement(statement, allTransactions);
	}
	
	public Statement createCheckingStatement(Statement statement, List<Transaction> allTransactions) {//statement with start date and account 
																										//all transaction for account
		statement.setTransactions(getTransactionForStatement(allTransactions, statement.getStartDate()));
		statement.setEndDate(java.sql.Date.valueOf(statement.getStartDate().toLocalDate().plusMonths(1)));
		statement.setStartingBalance(getStartingBalance(statement.getTransactions(), getStartDateOfTransaction(statement.getTransactions())));
		statement.setEndingBalance(getEndingBalance(statement.getTransactions(), getLastDateOfTransaction(statement.getTransactions())));
		statement.setTotalDeposit(getTotalDeposit(statement.getTransactions()));
		statement.setTotalWithdraw(getTotalWithdrawal(statement.getTransactions()));
		statement.setMonthName(statement.getStartDate().toLocalDate().getMonth().toString());
		
		return statement;
	}
	
	private List<Transaction> getTransactionForStatement(List<Transaction> allTransactions, Date startDate){
		return allTransactions.stream()
				.filter(t->t.getTransactionDate().after(startDate))
				.filter(t->t.getTransactionDate().before(java.sql.Date.valueOf(startDate.toLocalDate().plusMonths(1))))//add 30 days
				.collect(Collectors.toList());
	}
	
	private Date getLastDateOfTransaction(List<Transaction> transactions){
		return transactions.stream()
				.map(Transaction::getTransactionDate)
				.max(java.sql.Date::compareTo)
				.get();
	}
	
	private Date getStartDateOfTransaction(List<Transaction> transactions){
		return transactions.stream()
				.map(Transaction::getTransactionDate)
				.min(java.sql.Date::compareTo)
				.get();
	}
	
	private Double getEndingBalance(List<Transaction> transactions, Date endingDate){
		return transactions.stream()
				 			.filter(t -> t.getTransactionDate().equals(endingDate))
				 			.map(Transaction::getEndingBalance)
				 			.collect(Collectors.toList()).get(0);
	}
	
	private Double getStartingBalance(List<Transaction> transactions, Date statrtingDate){
		return transactions.stream()
						   .filter(t -> t.getTransactionDate().equals(statrtingDate))
						   .map(Transaction::getStartingBalance)
						   .collect(Collectors.toList()).get(0);		
	}
	
	private Double getTotalWithdrawal(List<Transaction> transactions){
		return transactions.stream()
						   .filter(t -> t.getTransactionType().equals(TransactionType.WITHDRAW) ||
									    t.getTransactionType().equals(TransactionType.TRANSFEREDFROM) ||
									    t.getTransactionType().equals(TransactionType.PAYCREDIT))
						   .map(t -> t.getTransactionAmount())
						   .reduce(0.0, (a1,a2) -> a1+a2);
	}
	
	private Double getTotalDeposit(List<Transaction> transactions){
		return transactions.stream()
				  		   .filter(t -> t.getTransactionType().equals(TransactionType.DEPOSIT) ||
				  				   		t.getTransactionType().equals(TransactionType.TRANSFEREDTO))
				  		   .map(t -> t.getTransactionAmount())
				  		   .reduce(0.0, (a1,a2) -> a1+a2);
	}
}
