package com.aurionpro.Lending.repository;

import java.util.List;

import com.aurionpro.Lending.entity.Customer;

public interface CustomerRepository {

	List<Customer> getAllCustomer();	
	Customer findCustomer(int customerId);
	void addCustomer(Customer customer);
	void updatecustomer(Customer customer);
	
}
