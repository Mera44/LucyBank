package com.lucy.builder;

import com.lucy.domain.Role;

public class RoleBuilder {
	private Role role;
	
	public RoleBuilder(){
		this.role = new Role();
	}
	
	public RoleBuilder withId(long id){
		this.role.setId(id);
		return this;
	}
	
	public RoleBuilder withRole(String role){
		this.role.setRole(role);
		return this;
	}
	public Role build(){
		return this.role;
	}
}
