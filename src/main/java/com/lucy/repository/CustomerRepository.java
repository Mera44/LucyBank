package com.lucy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucy.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	@Query(value="SELECT c FROM Customer c WHERE c.profile.userName = :userName")
	public Customer findCustomerByUsername(@Param("userName") String userName);
}
