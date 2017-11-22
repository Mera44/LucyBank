package com.lucy.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.lucy.domain.Address;
import com.lucy.repository.AddressRepository;
import com.lucy.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository  addressRepository;
	
	@PreAuthorize("hasRole('ROLE_BANKER')")
	@Override
	public void save(Address add) {
		addressRepository.save(add);
	}
	@Override
	public Address findAddressById(Long id) {
		
		return (Address)addressRepository.findOne(id);
	}
	
	
	@Override
	public void update(Address add) {
		
		addressRepository.save(add);
	}

}
