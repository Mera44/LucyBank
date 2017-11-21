package com.lucy.builder;

import com.lucy.domain.Address;
import com.lucy.domain.Profile;
import com.lucy.domain.Role;

public class ProfileBuilder {
	private Profile profile;
	
	public ProfileBuilder() {
		this.profile = new Profile();
	}
	
	public ProfileBuilder withId(long id){
		this.profile.setId(id);
		return this;
	}
	
	public ProfileBuilder withFistName(String fName){
		this.profile.setFirstName(fName);
		return this;
	}
	
	public ProfileBuilder withLastName(String lName){
		this.profile.setLastName(lName);
		return this;
	}
	
	public ProfileBuilder withUserName(String username){
		this.profile.setUserName(username);
		return this;		
	}
	
	public ProfileBuilder withPassword(String password){
		this.profile.setPassword(password);
		return this;
	}
	
	public ProfileBuilder withEmail(String email){
		this.profile.setEmail(email);
		return this;
	}
	
	public ProfileBuilder withAddress(Address address){
		this.profile.setAddress(address);
		return this;
	}
	
	public ProfileBuilder withRole(Role role){
		this.profile.setRole(role);
		return this;
	}
}
