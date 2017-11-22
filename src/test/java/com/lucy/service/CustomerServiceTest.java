package com.lucy.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lucy.builder.AddressBuilder;
import com.lucy.builder.CustomerBuilder;
import com.lucy.builder.ProfileBuilder;
import com.lucy.builder.RoleBuilder;
import com.lucy.domain.Role;
import com.lucy.repository.CustomerRepository;
import com.lucy.serviceImpl.CustomerServiceImpl;

public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepo;
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	CustomerBuilder custmer1;
	CustomerBuilder custmer2;
	CustomerBuilder custmer3;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		Role bankerRole = new RoleBuilder()
							 .withId(1L)
							 .withRole("ROLE_BANKER")
							 .build();
		Role tellerRole = new RoleBuilder()
							 .withId(1L)
							 .withRole("ROLE_TELLER")
							 .build();
		Role customerRole = new RoleBuilder()
								 .withId(1L)
								 .withRole("ROLE_CUSTOMER")
								 .build();
		custmer1 = new CustomerBuilder()
					   .withId((int) 1L)
					   .withProfile(new ProfileBuilder()
							   .withId(1L)
							   .withFistName("Ameha")
							   .withLastName("WAA-EA")
							   .withUserName("ameha")
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
				   .withId((int) 1L)
				   .withProfile(new ProfileBuilder()
						   .withId(1L)
						   .withFistName("Mera")
						   .withLastName("MWA")
						   .withUserName("mera12")
						   .withPassword("mera12")
						   .withRole(customerRole)
						   .withAddress(new AddressBuilder()
								   			.withId(1L)
								   			.withStreet("1000 4th St")
								   			.withState("IA")
								   			.withZipCode("52556")
								   			.build()
								   				)
						   .build()
						   );
		custmer3 = new CustomerBuilder()
				   .withId((int) 1L)
				   .withProfile(new ProfileBuilder()
						   .withId(1L)
						   .withFistName("Fili12")
						   .withLastName("Aman12")
						   .withUserName("fili12")
						   .withPassword("fili12")
						   .withRole(tellerRole)
						   .withAddress(new AddressBuilder()
								   			.withId(1L)
								   			.withStreet("2000 court St")
								   			.withState("IA")
								   			.withZipCode("52556")
								   			.build()
								   				)
						   .build()
						   );
	}
}
