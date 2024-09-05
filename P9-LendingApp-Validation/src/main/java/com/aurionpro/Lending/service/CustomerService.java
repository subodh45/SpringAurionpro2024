package com.aurionpro.Lending.service;

import java.util.List;

import com.aurionpro.Lending.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomer();	
	Customer findCustomer(int customerId);
	void addcustomer(Customer customer);
	void updateCustomer(Customer customer);
}
