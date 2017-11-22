package com.lucy.builder;

import java.util.Calendar;

import com.lucy.domain.TransactionType;

public class TransactionReady {
	

	
	TransactionBuilder depositTC = new TransactionBuilder()
										.withId(1L)
										.withTransactionAmount(200.0)
										.withStartingBalance(0.0)
										.withEndingBalance(200.0)
										.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
										.withTransactionType(TransactionType.DEPOSIT);

	TransactionBuilder withdrawTC = new TransactionBuilder()
										.withId(2L)
										.withTransactionAmount(100.0)
										.withStartingBalance(200.0)
										.withEndingBalance(100.0)
										.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
										.withTransactionType(TransactionType.WITHDRAW);
	
	TransactionBuilder depositTS2 = new TransactionBuilder()
										.withId(3L)
										.withTransactionAmount(400.0)
										.withStartingBalance(0.0)
										.withEndingBalance(400.0)
										.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
										.withTransactionType(TransactionType.DEPOSIT);
	TransactionBuilder withdrawTS2 = new TransactionBuilder()
											.withId(4L)
											.withTransactionAmount(100.0)
											.withStartingBalance(400.0)
											.withEndingBalance(300.0)
											.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
											.withTransactionType(TransactionType.WITHDRAW);
	
	TransactionBuilder depositTS = new TransactionBuilder()
										.withId(3L)
										.withTransactionAmount(400.0)
										.withStartingBalance(0.0)
										.withEndingBalance(400.0)
										.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
										.withTransactionType(TransactionType.DEPOSIT);
	TransactionBuilder withdrawTS = new TransactionBuilder()
										.withId(4L)
										.withTransactionAmount(100.0)
										.withStartingBalance(400.0)
										.withEndingBalance(300.0)
										.withTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()))
										.withTransactionType(TransactionType.WITHDRAW);
	
}
