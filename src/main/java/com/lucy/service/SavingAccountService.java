package com.lucy.service;

import java.util.List;

import com.lucy.domain.SavingAccount;

public interface SavingAccountService {
	
	public SavingAccount findById(long id);
	public List<SavingAccount> findAll();
	public SavingAccount save(SavingAccount account);
	public SavingAccount update(SavingAccount account);
	public boolean delete(long id);
	public SavingAccount getByAccountNumber(Integer accNo);
	public SavingAccount withdraw(Integer accNo, double amount);
	public SavingAccount deposit(Integer accNo, double amount);
	public boolean transfer(Integer transferFrom, Integer transferTo, double amount);
}