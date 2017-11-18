package com.lucy.service;

import java.util.List;

import com.lucy.domain.SavingAccount;
import com.lucy.domain.Transaction;

public interface SavingAccountService {
	
	public SavingAccount findById(long id);
	public List<SavingAccount> findAll();
	public SavingAccount save(SavingAccount account);
	public SavingAccount update(SavingAccount account);
	public boolean delete(Integer accNo);
	public SavingAccount getByAccountNumber(Integer accNo);
	public boolean withdraw(Integer accNo, Transaction transaction);
	public SavingAccount deposit(Integer accNo, Transaction transaction);
	public boolean transfer(Integer transferFrom, Integer transferTo, Transaction transaction);
}