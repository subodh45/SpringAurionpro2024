package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.entity.Customer;

public interface CustomerService {

	
	List<Customer> getAllCustomers();
	
	Customer getCustomerById(int customerId);
}
