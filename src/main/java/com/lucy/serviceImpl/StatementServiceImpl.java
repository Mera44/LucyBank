package com.lucy.serviceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Statement;
import com.lucy.repository.StatementRepository;
import com.lucy.service.StatementService;

@Service
public class StatementServiceImpl implements StatementService {

	@Autowired
	StatementRepository statementRepository;
	
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
	public Statement createStatement(Integer accNo, Date startDate) {
		
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
