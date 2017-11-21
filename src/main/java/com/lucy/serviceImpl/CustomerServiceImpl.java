package com.lucy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.domain.Customer;
import com.lucy.repository.CustomerRepository;
import com.lucy.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired 
	CustomerRepository customerRepository;
	
	@Override
	public Customer getCustomer(long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public List<Customer> getCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public void update(Customer customer, long id) {
		Customer updatableCustomer = customerRepository.findOne(id);
		if(updatableCustomer==null) {
			//handle Error here
		}
		customer.setId(id);
		customerRepository.save(customer);
		
	}

	@Override
	public void deleteCustomer(long id) {
		Customer customer = customerRepository.findOne(id);
		if(customer==null) {
			//handle Error here
		}
		customerRepository.delete(id);
	}

	@Override
	public Customer findByProfileUserName(String userName) {
		return customerRepository.findByProfileUserName(userName);
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
		
	}
	

}
