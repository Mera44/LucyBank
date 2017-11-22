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
import com.lucy.domain.CheckingAccount;
import com.lucy.repository.CheckingAccountRepository;
import com.lucy.serviceImpl.CheckingAccountServiceImpl;

public class CheckingServiceTest {
	@Mock
	private CheckingAccountRepository CheckingRepoMock;
	@InjectMocks
	private CheckingAccountServiceImpl checkingService;
	
	CheckingAccount oneAcc;
	List<CheckingAccount> checkingList = new ArrayList<CheckingAccount>();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		CustomerListBuilder listBuilder = new CustomerListBuilder();
		checkingList = listBuilder.getAllChakingAccount();
		oneAcc = listBuilder.getCheckingAccount();
		}
	
	@Test
	public void getAll() throws Exception{
		when(CheckingRepoMock.findAll()).thenReturn(checkingList);
		List<CheckingAccount> accounts = checkingService.findAll();
		//Customer cust = custmer1.build();
		assertThat(accounts, hasItem(
				allOf(
						hasProperty("id", is(oneAcc.getId())),
						hasProperty("balance", is(oneAcc.getBalance())),
						hasProperty("transaction", hasItem(oneAcc.getTransaction().get(0)))
						)));
		assertThat(accounts, hasItem(
				allOf(
						hasProperty("id", is(1L)),
						hasProperty("balance", is(300.0))
						
						)
				));
	}
	
	
}