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
import com.lucy.domain.Role;
import com.lucy.repository.RoleRepository;
import com.lucy.serviceImpl.RoleServiceImpl;

public class RoleServiceTest {
	@Mock
	private RoleRepository roleRepoMock;
	@InjectMocks
	private RoleServiceImpl roleService;
	
	Role role;
	
	List<Role> roleList = new ArrayList<Role>();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		CustomerListBuilder listBuilder = new CustomerListBuilder();
		role = listBuilder.getRole();
		roleList = listBuilder.getAllRoles();		
	}
	
	@Test
	public void getAll() throws Exception{
		when(roleRepoMock.findAll()).thenReturn(roleList);
		List<Role> roles = roleService.getAll();
		Role otherR = roleList.get(1);
		assertThat(roles, hasItem(
				allOf(
						hasProperty("id", is(otherR.getId())),
						hasProperty("role", is(otherR.getRole()))
						)));
		assertThat(roles, hasItem(
				allOf(
						hasProperty("id", is(1L)),
						hasProperty("role", is("ROLE_BANKER"))						
						)
				));
	}
	
	@Test
	public void getOne() throws Exception{
		Role otherr = roleList.get(2);
		when(roleRepoMock.findByRole(otherr.getRole())).thenReturn(otherr);		
		Role roleBack = roleService.getByRole("ROLE_CUSTOMER");
		assertThat(roleBack, 
				allOf(
						hasProperty("id", is(otherr.getId())),
						hasProperty("role", is(otherr.getRole()))
						));
		assertThat(roleBack, 
				allOf(
						hasProperty("id", is(1L)),
						hasProperty("role", is("ROLE_CUSTOMER"))
						
						)
				);
	}
}
