package com.lucy.serviceImpl;

import java.util.List;

import com.lucy.domain.SavingAccount;
import com.lucy.service.SavingAccountService;

public class SavingAccountServiceImpl implements SavingAccountService {

	@Override
	public SavingAccount findById(long id) {
		
		return null;
	}

	@Override
	public List<SavingAccount> findAll() {
		
		return null;
	}

	@Override
	public SavingAccount save(SavingAccount account) {
		
		return null;
	}

	@Override
	public SavingAccount update(SavingAccount account) {
		
		return null;
	}

	@Override
	public boolean delete(long id) {
		
		return false;
	}

	@Override
	public SavingAccount getByAccountNumber(Integer accNo) {
		
		return null;
	}

	@Override
	public SavingAccount withdraw(Integer accNo, double amount) {
		
		return null;
	}

	@Override
	public SavingAccount deposit(Integer accNo, double amount) {
		
		return null;
	}

	@Override
	public boolean transfer(Integer transferFrom, Integer transferTo, double amount) {
		
		return false;
	}

}
