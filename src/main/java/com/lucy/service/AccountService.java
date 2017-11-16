package com.lucy.service;

import java.util.List;

import com.lucy.domain.Account;

public interface AccountService {
	public Account findById(long id);
	public List<Account> findAll();
	public Account update(Account account);
	public boolean delete(long id);
	public Account save(Account account);
}
