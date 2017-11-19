package com.lucy.service;

import java.sql.Date;
import java.util.List;

import com.lucy.domain.Statement;

public interface StatementService {
	
	public Statement findById(long id);
	public List<Statement> findAll();
	public Statement update(Statement statement);
	public boolean delete(long id);
	public Statement save(Statement statement);
	public Statement createCheckingStatement(Statement statement);//statement has id and start date
	public Statement createSavingStatement(Statement statement);
	public Statement createCreditStatement(Statement statement);
	public List<Statement> getByAccountNumber(Integer aacNo);
	public Statement getByDate(Date date);
	public Statement getByMonthName(String monthName);
}
