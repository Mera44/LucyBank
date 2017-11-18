package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Account;
import com.lucy.domain.CheckingAccount;
import com.lucy.repository.AccountRepository;
import com.lucy.repository.CheckingAccountRepository;
import com.lucy.service.CheckingAccountService;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {

	@Autowired
	CheckingAccountRepository checkingRepository;
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public CheckingAccount findById(long id) {		
		return checkingRepository.findOne(id);
	}

	@Override
	public List<CheckingAccount> findAll() {		
		return (List<CheckingAccount>) checkingRepository.findAll();
	}

	@Override
	public CheckingAccount save(CheckingAccount account) {		
		return checkingRepository.save(account);
	}

	@Override
	public CheckingAccount update(CheckingAccount account) {		
		return checkingRepository.save(account);
	}

	@Override
	public boolean delete(Integer accNo) {
		CheckingAccount account = checkingRepository.findByAccountNumber(accNo);
		if(account != null){
			account.setStatus(false);
			save(account);
			return true;
		}
		return false;
	}

	@Override
	public CheckingAccount getByAccountNumber(Integer accNo) {		
		return checkingRepository.findByAccountNumber(accNo);
	}

	@Override
	public boolean withdraw(Integer accNo, double amount) {
		CheckingAccount account = getByAccountNumber(accNo);
		if(account.getBalance()>amount){
			account.setBalance(account.getBalance()-amount);
			update(account);
			return true;
		}
		return false;//if available balance is less than withdraw amount 
	}

	@Override
	public CheckingAccount deposit(Integer accNo, double amount) {
		CheckingAccount account = getByAccountNumber(accNo);
		account.setBalance(account.getBalance()+amount);
		return checkingRepository.save(account);
	}

	@Override
	public boolean transfer(Integer transferFrom, Integer transferTo, double amount) {
		CheckingAccount accountFrom = getByAccountNumber(transferFrom);
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
