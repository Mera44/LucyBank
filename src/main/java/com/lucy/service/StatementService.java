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
	public Statement createStatement(Integer accNo, Date startDate);
	public List<Statement> getByAccountNumber(Integer aacNo);
	public Statement getByDate(Date date);
	public Statement getByMonthName(String monthName);
}
