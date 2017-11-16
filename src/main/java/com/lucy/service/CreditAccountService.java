package com.lucy.service;

import java.util.List;

import com.lucy.domain.CreditAccount;

public interface CreditAccountService {
	
	public CreditAccount findById(long id);
	public List<CreditAccount> findAll();
	public CreditAccount save(CreditAccount account);
	public CreditAccount update(CreditAccount account);
	public boolean delete(long id);
	public CreditAccount getByAccountNumber(Integer accNo);
	public boolean payBill(Integer payFrom, Integer payTo, double amount);
}