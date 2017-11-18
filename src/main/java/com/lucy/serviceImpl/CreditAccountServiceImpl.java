package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucy.domain.CreditAccount;
import com.lucy.service.CreditAccountService;

@Service
public class CreditAccountServiceImpl implements CreditAccountService {

	@Override
	public CreditAccount findById(long id) {
		
		return null;
	}

	@Override
	public List<CreditAccount> findAll() {
		
		return null;
	}

	@Override
	public CreditAccount save(CreditAccount account) {
		
		return null;
	}

	@Override
	public CreditAccount update(CreditAccount account) {
		
		return null;
	}

	@Override
	public boolean delete(Integer accNo) {
		
		return false;
	}

	@Override
	public CreditAccount getByAccountNumber(Integer accNo) {
		
		return null;
	}

	@Override
	public boolean payBill(Integer payFrom, Integer payTo, double amount) {
		
		return false;
	}

}
