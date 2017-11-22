package com.lucy.service;

import com.lucy.domain.Address;

public interface AddressService {

	void update(Address add);
	void save(Address add);
	public Address findAddressById(Long id);
}
