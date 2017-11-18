package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Account;
import com.lucy.repository.AccountRepository;
import com.lucy.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account findById(long id) {
		return accountRepository.findOne(id);
	}

	@Override
	public List<Account> findAll() {
		return (List<Account>)accountRepository.findAll();
	}

	@Override
	public Account update(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public boolean delete(Integer accNo) {
		Account account = accountRepository.findByAccountNumber(accNo);
		if(account != null){
			account.setStatus(false);
			save(account);
			return true;
		}
		return false;
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

}
