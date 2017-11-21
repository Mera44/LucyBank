package com.lucy.service;

import com.lucy.domain.Address;

public interface AddressService {

	void save(Address add);
	public Address findAddressById(Long id);
}
