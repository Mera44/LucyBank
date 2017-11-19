package com.lucy.serviceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Statement;
import com.lucy.repository.CheckingAccountRepository;
import com.lucy.repository.CreditAccountRepository;
import com.lucy.repository.SavingAccountRepository;
import com.lucy.repository.StatementRepository;
import com.lucy.service.StatementService;

@Service
public class StatementServiceImpl implements StatementService {

	@Autowired
	StatementRepository statementRepository;
	@Autowired
	CheckingAccountRepository checkingRepository;
	@Autowired
	SavingAccountRepository savingRepository;
	@Autowired
	CreditAccountRepository creditRepository;
	@Autowired
	StatementHelper statementHelper;
	
	@Override
	public Statement findById(long id) {		
		return statementRepository.findOne(id);
	}

	@Override
	public List<Statement> findAll() {		
		return (List<Statement>) statementRepository.findAll();
	}

	@Override
	public Statement update(Statement statement) {		
		return statementRepository.save(statement);
	}

	@Override
	public boolean delete(long id) {		
		return false;
	}

	@Override
	public Statement save(Statement statement) {		
		return statementRepository.save(statement);
	}

	@Override
	public Statement createCheckingStatement(Statement statement) {
		statement.setAccount(checkingRepository.findOne(statement.getAccount().getId()));		
		return statementHelper.createCheckingStatement(statement,
						checkingRepository.findOne(statement.getAccount().getId()).getTransaction());
	}
	
	@Override
	public Statement createSavingStatement(Statement statement) {
		statement.setAccount(savingRepository.findOne(statement.getAccount().getId()));	
		return statementHelper.createSavingStatement(statement, 
						savingRepository.findOne(statement.getAccount().getId()).getTransaction());
	}

	@Override
	public Statement createCreditStatement(Statement statement) {
		return null;
	}

	@Override
	public List<Statement> getByAccountNumber(Integer aacNo) {		
		return statementRepository.findByAccountAccountNumber(aacNo);
	}

	@Override
	public Statement getByDate(Date date) {		
		return statementRepository.findByDate(date);
	}

	@Override
	public Statement getByMonthName(String monthName) {
		return statementRepository.findByMonthName(monthName);
	}
}
