package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Account;
import com.lucy.domain.SavingAccount;
import com.lucy.repository.AccountRepository;
import com.lucy.repository.SavingAccountRepository;
import com.lucy.service.SavingAccountService;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

	@Autowired
	SavingAccountRepository savingRepository;
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public SavingAccount findById(long id) {		
		return savingRepository.findOne(id);
	}

	@Override
	public List<SavingAccount> findAll() {		
		return (List<SavingAccount>) savingRepository.findAll();
	}

	@Override
	public SavingAccount save(SavingAccount account) {		
		return savingRepository.save(account);
	}

	@Override
	public SavingAccount update(SavingAccount account) {		
		return savingRepository.save(account);
	}

	@Override
	public boolean delete(Integer accNo) {		
		SavingAccount account = savingRepository.findByAccountNumber(accNo);
		if(account != null){
			account.setStatus(false);
			save(account);
			return true;
		}
		return false;
	}

	@Override
	public SavingAccount getByAccountNumber(Integer accNo) {		
		return savingRepository.findByAccountNumber(accNo);
	}

	@Override
	public boolean withdraw(Integer accNo, double amount) {
		SavingAccount account = getByAccountNumber(accNo);
		if(account.getBalance()>amount){
			account.setBalance(account.getBalance()-amount);
			update(account);
			return true;
		}
		return false;
	}

	@Override
	public SavingAccount deposit(Integer accNo, double amount) {		
		SavingAccount account = getByAccountNumber(accNo);
		account.setBalance(account.getBalance()+amount);
		return savingRepository.save(account);
	}

	@Override
	public boolean transfer(Integer transferFrom, Integer transferTo, double amount) {		
		SavingAccount accountFrom = getByAccountNumber(transferFrom);
		Account accountTo = accountRepository.findByAccountNumber(transferTo);
		if(accountFrom.getBalance()>amount){
			accountFrom.setBalance(accountFrom.getBalance()-amount);
			accountTo.setBalance(accountTo.getBalance()+amount);
			save(accountFrom);
			accountRepository.save(accountTo);
			return true;
		}
		return false;
	}

}
