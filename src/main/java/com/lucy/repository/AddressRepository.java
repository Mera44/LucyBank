package com.lucy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Address;
@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
