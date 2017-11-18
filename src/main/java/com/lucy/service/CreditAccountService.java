package com.lucy.service;

import java.util.List;

import com.lucy.domain.CreditAccount;
import com.lucy.domain.Transaction;

public interface CreditAccountService {
	
	public CreditAccount findById(long id);
	public List<CreditAccount> findAll();
	public CreditAccount save(CreditAccount account);
	public CreditAccount update(CreditAccount account);
	public boolean delete(Integer accNo);
	public CreditAccount getByAccountNumber(Integer accNo);
	public boolean chargeCredit(Integer accNo, Transaction transaction);
	public boolean payMonthlyBill(Integer payFrom, Integer payTo, Transaction transaction);
}