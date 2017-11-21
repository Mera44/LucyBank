package com.lucy.webstore.webflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucy.domain.Account;
import com.lucy.domain.Statement;
import com.lucy.service.AccountService;
import com.lucy.service.StatementService;


@Component
public class StatementControllerHelper {

	@Autowired
	StatementService statementService;
	@Autowired
	AccountService accountService;
	
/*	public Long saveOrder(Order order) {
		return orderService.saveOrder(order);
	}*/
	
	public Account getAccount(Long accId) {
		System.out.println("======> Web flow Helper ");
		System.out.println("======> Web flow Helper "+ accId);
		
		return accountService.findById(accId);
		
	}
	

	public Statement createStatement(Statement statement) {
		System.out.println("======> Web flow Helper 2 date  " + statement.getStartDate());
		Statement stat;
		if (statement.getAccount().getTypeAccount().equalsIgnoreCase("Checking")) {
			stat = (Statement)statementService.createCheckingStatement(statement);
		}
		else if (statement.getAccount().getTypeAccount().equalsIgnoreCase("Saving")) {
			stat = (Statement)statementService.createSavingStatement(statement);
		}
		else {
			stat = (Statement)statementService.createCreditStatement(statement);
		}
		
		return stat;
		
	}
	
	
}
