package com.lucy.builder;

import com.lucy.domain.Address;

public class AddressBuilder {
	private Address address;
	
	public AddressBuilder(){
		this.address = new Address();
	}
	
	public AddressBuilder withId(long id){
		this.address.setId(id);
		return this;
	}
	
	public AddressBuilder withStreet(String street){
		this.address.setStreet(street);
		return this;
	}
	
	public AddressBuilder withState(String state){
		this.address.setState(state);
		return this;
	}
	
	public AddressBuilder withZipCode(String zipcode){
		this.address.setZipcode(zipcode);
		return this;
	}
	
	public Address build(){
		return this.address;
	}
}
