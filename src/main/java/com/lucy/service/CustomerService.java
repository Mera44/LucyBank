package com.lucy.service;

import java.util.List;

import com.lucy.domain.Customer;

public interface CustomerService {
	public void save(Customer customer);
	public Customer getCustomer(long id);
	public List<Customer> getCustomers();
	public void update(Customer customer, long id);
	public void deleteCustomer(long id);
	public Customer findCustomerByUsername(String userName);
}
