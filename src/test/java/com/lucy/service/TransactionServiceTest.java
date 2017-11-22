package com.lucy.service;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lucy.builder.CustomerListBuilder;
import com.lucy.builder.TransactionBuilder;
import com.lucy.domain.Transaction;
import com.lucy.domain.TransactionType;
import com.lucy.repository.TransactionRepository;
import com.lucy.serviceImpl.TransactionServiceImpl;

public class TransactionServiceTest {
	@Mock
	private TransactionRepository transactionRepoMock;
	@InjectMocks
	private TransactionServiceImpl transactionService;
	
	TransactionBuilder tx;
	
	List<Transaction> transactionList = new ArrayList<Transaction>();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		CustomerListBuilder listBuilder = new CustomerListBuilder();
		transactionList = listBuilder.getTransactions();
		tx = listBuilder.getOneTransactionBuilder();
	}
	
	@Test
	public void getAll() throws Exception{
		when(transactionRepoMock.findAll()).thenReturn(transactionList);
		List<Transaction> transactions = transactionService.getAllTransaction();
		Transaction transaction = tx.build();
		assertThat(transactions, hasItem(
				allOf(
						hasProperty("id", is(transaction.getId())),
						hasProperty("transactionDate", is(transaction.getTransactionDate())),
						hasProperty("endingBalance", is(transaction.getEndingBalance()))
						)));
		assertThat(transactions, hasItem(
				allOf(
						hasProperty("id", is(1L)),
						hasProperty("endingBalance", is(200.0)),
						hasProperty("transactionType", is(TransactionType.DEPOSIT))
						
						)
				));
	}
	
	@Test
	public void getOne() throws Exception{
		Transaction transaction = tx.build();
		when(transactionRepoMock.findOne(transaction.getId())).thenReturn(transaction);		
		Transaction tBack = transactionService.findById(5L);
		assertThat(tBack, 
				allOf(
						hasProperty("id", is(tBack.getId())),
						hasProperty("startingBalance", is(tBack.getStartingBalance())),
						hasProperty("transactionAmount", is(tBack.getTransactionAmount()))
						));
		assertThat(tBack, 
				allOf(
						hasProperty("id", is(5L)),
						hasProperty("startingBalance",  is(0.0)),
						hasProperty("transactionAmount", is(200.0))
						
						)
				);
	}
}
