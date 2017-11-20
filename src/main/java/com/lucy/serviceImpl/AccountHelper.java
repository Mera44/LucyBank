package com.lucy.serviceImpl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lucy.domain.Account;
import com.lucy.domain.CreditAccount;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;

@Component
public class AccountHelper {
	
	public Account withdraw(Account account, Transaction transaction){
		if(account.getBalance()>transaction.getTransactionAmount()){
			transaction.setTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			transaction.setStartingBalance(account.getBalance());
			account.setBalance(account.getBalance()-transaction.getTransactionAmount());
			transaction.setEndingBalance(account.getBalance());
			account.addTransaction(transaction);
			return account;
		}
		return null;//if available balance is less than withdraw amount 
	}
	public Account deposit(Account account, Transaction transaction){
		System.out.println("=======> amounthelper  " + transaction.getTransactionAmount());
		
		transaction.setTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		transaction.setStartingBalance(account.getBalance());
		account.setBalance(account.getBalance()+(double)transaction.getTransactionAmount());
		transaction.setEndingBalance(account.getBalance());
		account.addTransaction(transaction);
		System.out.println("=======> depositHelperTransEndBalance   " + transaction.getEndingBalance());
		return account;
	}
	public List<Account> transfer(Account transferFrom, Account transferTo, Transaction transaction){
		Transaction transFrom = new Transaction();
		transFrom.setTransactionAmount(transaction.getTransactionAmount());
		Transaction transTo = new Transaction();
		transTo.setTransactionAmount(transaction.getTransactionAmount());
		if(withdraw(transferFrom, transaction)!=null)
			return Arrays.asList(withdraw(transferFrom, transFrom.setTransactionTypeFor(TransactionType.TRANSFEREDTO)), deposit(transferTo, transTo.setTransactionTypeFor(TransactionType.TRANSFEREDFROM)));
		return null;
	}
	
	public List<Account> payBill(Account creditAccount, Account payFrom, Transaction transaction){
		if(withdraw(payFrom, transaction)!=null){
			return Arrays.asList(deposit(creditAccount, transaction), withdraw(payFrom, transaction.setTransactionTypeFor(TransactionType.PAYCREDIT)));
		}
		return null;
	}
	
	public CreditAccount payCreditCardTo(CreditAccount credit, Transaction transaction){		
		transaction.setTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		transaction.setStartingBalance(credit.getBalance());
		credit.setBalance(credit.getBalance()+transaction.getTransactionAmount());
		transaction.setEndingBalance(credit.getBalance());
		credit.addTransaction(transaction);
		return credit;
	}

}
