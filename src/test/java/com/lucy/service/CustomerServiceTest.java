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

import com.lucy.builder.AddressBuilder;
import com.lucy.builder.CustomerBuilder;
import com.lucy.builder.ProfileBuilder;
import com.lucy.builder.RoleBuilder;
import com.lucy.domain.Customer;
import com.lucy.domain.Role;
import com.lucy.repository.CustomerRepository;
import com.lucy.serviceImpl.CustomerServiceImpl;

public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepoMock;
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	CustomerBuilder custmer1;
	CustomerBuilder custmer2;
	CustomerBuilder custmer3;
	List<Customer> customerList = new ArrayList<Customer>();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		Role bankerRole = new RoleBuilder()
							 .withId(1L)
							 .withRole("ROLE_BANKER")
							 .build();
		Role tellerRole = new RoleBuilder()
							 .withId(2L)
							 .withRole("ROLE_TELLER")
							 .build();
		Role customerRole = new RoleBuilder()
								 .withId(3L)
								 .withRole("ROLE_CUSTOMER")
								 .build();
		custmer1 = new CustomerBuilder()
					   .withId(1L)
					   .withProfile(new ProfileBuilder()
							   .withId(1L)
							   .withFistName("Ameha")
							   .withLastName("WAA-EA")
							   .withUserName("ameha")
							   .withEmail("ameha@mum.edu")
							   .withPassword("ameha")
							   .withRole(bankerRole)
							   .withAddress(new AddressBuilder()
									   			.withId(1L)
									   			.withStreet("4 Main St")
									   			.withState("IA")
									   			.withZipCode("52556")
									   			.build()
									   				)
							   .build()
							   );
		custmer2 = new CustomerBuilder()
				   .withId(2L)
				   .withProfile(new ProfileBuilder()
						   .withId(1L)
						   .withFistName("Mera")
						   .withLastName("MWA")
						   .withUserName("mera12")
						   .withEmail("mera@mum.edu")
						   .withPassword("mera12")
						   .withRole(customerRole)
						   .withAddress(new AddressBuilder()
								   			.withId(2L)
								   			.withStreet("1000 4th St")
								   			.withState("IA")
								   			.withZipCode("52556")
								   			.build()
								   				)
						   .build()
						   );
		custmer3 = new CustomerBuilder()
				   .withId(3L)
				   .withProfile(new ProfileBuilder()
						   .withId(1L)
						   .withFistName("Fili12")
						   .withLastName("Aman12")
						   .withUserName("fili12")
						   .withEmail("aman@mum.edu")
						   .withPassword("fili12")
						   .withRole(tellerRole)
						   .withAddress(new AddressBuilder()
								   			.withId(3L)
								   			.withStreet("2000 court St")
								   			.withState("IA")
								   			.withZipCode("52556")
								   			.build()
								   				)
						   .build()
						   );
		customerList.add(custmer1.build());
		customerList.add(custmer2.build());
		customerList.add(custmer3.build());
	}
	
	@Test
	public void getAll() throws Exception{
		when(customerRepoMock.findAll()).thenReturn(customerList);
		List<Customer> customers = customerService.getCustomers();
		Customer cust = custmer1.build();
		assertThat(customers, hasItem(
				allOf(
						hasProperty("id", is(cust.getId())),
						hasProperty("profile", hasProperty("email", is(cust.getProfile().getEmail()))),
						hasProperty("profile", hasProperty("address",hasProperty("state", is(cust.getProfile().getAddress().getState()))))
						)));
		assertThat(customers, hasItem(
				allOf(
						hasProperty("id", is(1L)),
						hasProperty("profile", hasProperty("email", is("ameha@mum.edu"))),
						hasProperty("profile", hasProperty("address",hasProperty("street", is("4 Main St"))))
						
						)
				));
	}
	
	@Test
	public void getOne() throws Exception{
		Customer customer = custmer3.build();
		when(customerRepoMock.findOne(customer.getId())).thenReturn(customer);		
		Customer custBack = customerService.getCustomer(3L);
		assertThat(custBack, 
				allOf(
						hasProperty("id", is(customer.getId())),
						hasProperty("profile", hasProperty("email", is(customer.getProfile().getEmail()))),
						hasProperty("profile", hasProperty("address",hasProperty("state", is(customer.getProfile().getAddress().getState()))))
						));
		assertThat(custBack, 
				allOf(
						hasProperty("id", is(3L)),
						hasProperty("profile", hasProperty("email", is("aman@mum.edu"))),
						hasProperty("profile", hasProperty("address",hasProperty("zipcode", is("52556"))))
						
						)
				);
	}
}
