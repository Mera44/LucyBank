package com.lucy.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Account;
import com.lucy.domain.CheckingAccount;
import com.lucy.domain.CreditAccount;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.repository.AccountRepository;
import com.lucy.repository.CheckingAccountRepository;
import com.lucy.repository.CreditAccountRepository;
import com.lucy.service.CheckingAccountService;

@Service
@Transactional
public class CheckingAccountServiceImpl implements CheckingAccountService {

	@Autowired
	CheckingAccountRepository checkingRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountHelper accountHelper;
	@Autowired
	CreditAccountRepository creditRepository;
	
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
	public boolean withdraw(Integer accNo, Transaction transaction) {
		/*CheckingAccount account = getByAccountNumber(accNo);
		if(account.getBalance()>transaction.getTransactionAmount()){
			transaction.setTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			transaction.setStartingBalance(account.getBalance());
			account.setBalance(account.getBalance()-transaction.getTransactionAmount());
			transaction.setEndingBalance(account.getBalance());
			account.addTransaction(transaction);
			update(account);
			return true;
		}
		return false;//if available balance is less than withdraw amount */
		//return accountHelper.withdraw(account, transaction)==null ? false:true;
		if(accountHelper.withdraw(getByAccountNumber(accNo), transaction)==null)
			return false;
		save((CheckingAccount)accountHelper.withdraw(getByAccountNumber(accNo), transaction));
		return true;
	}

	@Override
	public CheckingAccount deposit(Integer accNo, Transaction transaction) {
		/*CheckingAccount account = getByAccountNumber(accNo);
		/*transaction.setTransactionDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		transaction.setStartingBalance(account.getBalance());
		account.setBalance(account.getBalance()+transaction.getTransactionAmount());
		transaction.setEndingBalance(account.getBalance());
		account.addTransaction(transaction);
		return save(account);*/
		
		return save((CheckingAccount)accountHelper.deposit(getByAccountNumber(accNo), transaction));
	}

	@Override
	public boolean transfer(Integer transferFrom, Integer transferTo, Transaction transaction) {
		/*CheckingAccount accountFrom = getByAccountNumber(transferFrom);
		Account accountTo = accountRepository.findByAccountNumber(transferTo);
		if(accountFrom.getBalance()>transaction.getTransactionAmount()){
			accountFrom.setBalance(accountFrom.getBalance()-transaction.getTransactionAmount());
			accountTo.setBalance(accountTo.getBalance()+transaction.getTransactionAmount());
			save(accountFrom);
			accountRepository.save(accountTo);
			return true;
		}
		return false;*/
		List<Account> accounts = accountHelper.transfer(getByAccountNumber(transferFrom), 
				accountRepository.findByAccountNumber(transferTo), transaction);
		if(!accounts.isEmpty() && (accounts != null)){
			accounts.forEach(accountRepository::save);
			//accountRepository.save(accounts.get(0));
			return true;
		}
		return false;
	}

	@Override
	public boolean payCreditBill(Integer payFrom, Integer credit, Transaction transaction) {		
		if(accountHelper.withdraw(checkingRepository.findByAccountNumber(payFrom), transaction)!=null){
			update((CheckingAccount) accountHelper.withdraw(checkingRepository.findByAccountNumber(payFrom), transaction));
			CreditAccount paidAccount = accountHelper.payCreditCardTo(creditRepository.findByAccountNumber(credit), 
					transaction.setTransactionTypeFor(TransactionType.PAYMENT));
			creditRepository.save(paidAccount);
			return true;
		}
		return false;
	}

}
