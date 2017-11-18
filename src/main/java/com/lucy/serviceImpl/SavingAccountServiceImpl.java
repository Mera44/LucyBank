package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Account;
import com.lucy.domain.SavingAccount;
import com.lucy.domain.Transaction;
import com.lucy.repository.AccountRepository;
import com.lucy.repository.SavingAccountRepository;
import com.lucy.service.SavingAccountService;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

	@Autowired
	SavingAccountRepository savingRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountHelper accountHelper;
	
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
	public boolean withdraw(Integer accNo, Transaction transaction) {
		/*SavingAccount account = getByAccountNumber(accNo);
		if(account.getBalance()>amount){
			account.setBalance(account.getBalance()-amount);
			update(account);
			return true;
		}
		return false;*/
		if(accountHelper.withdraw(getByAccountNumber(accNo), transaction)==null)
			return false;
		save((SavingAccount)accountHelper.withdraw(getByAccountNumber(accNo), transaction));
		return true;
	}

	@Override
	public SavingAccount deposit(Integer accNo, Transaction transaction) {		
		/*SavingAccount account = getByAccountNumber(accNo);
		account.setBalance(account.getBalance()+amount);
		return savingRepository.save(account);*/
		return save((SavingAccount)accountHelper.deposit(getByAccountNumber(accNo), transaction));
	}

	@Override
	public boolean transfer(Integer transferFrom, Integer transferTo, Transaction transaction) {		
		/*SavingAccount accountFrom = getByAccountNumber(transferFrom);
		Account accountTo = accountRepository.findByAccountNumber(transferTo);
		if(accountFrom.getBalance()>amount){
			accountFrom.setBalance(accountFrom.getBalance()-amount);
			accountTo.setBalance(accountTo.getBalance()+amount);
			save(accountFrom);
			accountRepository.save(accountTo);
			return true;
		}
		return false;*/
		List<Account> accounts = accountHelper.transfer(getByAccountNumber(transferFrom),
				accountRepository.findByAccountNumber(transferTo), transaction);
		if(!accounts.isEmpty() && !(accounts == null)){
			accounts.forEach(accountRepository::save);
			return true;
		}
		return false;
	}

}
