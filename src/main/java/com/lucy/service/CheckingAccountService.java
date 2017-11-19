package com.lucy.service;

import java.util.List;

import com.lucy.domain.CheckingAccount;
import com.lucy.domain.Transaction;

public interface CheckingAccountService {
	
	public CheckingAccount findById(long id);
	public List<CheckingAccount> findAll();
	public CheckingAccount save(CheckingAccount account);
	public CheckingAccount update(CheckingAccount account);
	public boolean delete(Integer accNo);
	public CheckingAccount getByAccountNumber(Integer accNo);
	public boolean withdraw(Integer accNo, Transaction transaction);
	public CheckingAccount deposit(Integer accNo, Transaction transaction);
	public boolean transfer(Integer transferFrom, Integer transferTo, Transaction transaction);//transaction type transferfrom
	public boolean payCreditBill(Integer payFrom, Integer credit, Transaction transaction);//transaction paycredit
}
