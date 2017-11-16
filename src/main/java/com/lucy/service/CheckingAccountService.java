package com.lucy.service;

import java.util.List;

import com.lucy.domain.CheckingAccount;

public interface CheckingAccountService {
	
	public CheckingAccount findById(long id);
	public List<CheckingAccount> findAll();
	public CheckingAccount save(CheckingAccount account);
	public CheckingAccount update(CheckingAccount account);
	public boolean delete(long id);
	public CheckingAccount getByAccountNumber(Integer accNo);
	public CheckingAccount withdraw(Integer accNo, double amount);
	public CheckingAccount deposit(Integer accNo, double amount);
}
