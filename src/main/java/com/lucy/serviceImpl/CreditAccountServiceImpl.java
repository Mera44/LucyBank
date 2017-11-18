package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Account;
import com.lucy.domain.CreditAccount;
import com.lucy.domain.Transaction;
import com.lucy.repository.AccountRepository;
import com.lucy.repository.CreditAccountRepository;
import com.lucy.service.CreditAccountService;

@Service
public class CreditAccountServiceImpl implements CreditAccountService {

	@Autowired
	CreditAccountRepository creditRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountHelper accountHelper;
	
	@Override
	public CreditAccount findById(long id) {		
		return creditRepository.findOne(id);
	}

	@Override
	public List<CreditAccount> findAll() {		
		return (List<CreditAccount>) creditRepository.findAll();
	}

	@Override
	public CreditAccount save(CreditAccount account) {		
		return creditRepository.save(account);
	}

	@Override
	public CreditAccount update(CreditAccount account) {		
		return creditRepository.save(account);
	}

	@Override
	public boolean delete(Integer accNo) {		
		CreditAccount account = creditRepository.findByAccountNumber(accNo);
		if(account != null){
			account.setStatus(false);
			save(account);
			return true;
		}
		return false;
	}

	@Override
	public CreditAccount getByAccountNumber(Integer accNo) {		
		return creditRepository.findByAccountNumber(accNo);
	}

	@Override
	public boolean payMonthlyBill(Integer payTo, Integer payFrom, Transaction transaction) {		
		List<Account> accounts = accountHelper.payBill(getByAccountNumber(payTo), getByAccountNumber(payFrom), transaction);
		if(!accounts.isEmpty() && !(accounts == null)){
			accounts.forEach(accountRepository::save);
			return true;
		}
		return false;
	}

	@Override
	public boolean chargeCredit(Integer accNo, Transaction transaction) {//transaction creditcharge	
		if(accountHelper.withdraw(getByAccountNumber(accNo), transaction)==null)
			return false;
		save((CreditAccount)accountHelper.withdraw(getByAccountNumber(accNo), transaction));
		return true;
	}

}
