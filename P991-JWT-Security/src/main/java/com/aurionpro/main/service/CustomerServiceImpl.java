package com.aurionpro.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.main.Repository.CustomerRepository;
import com.aurionpro.main.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerRepo.findAll();
		return customers;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Customer customer = customerRepo.findById(customerId).orElseThrow(()-> new NullPointerException("student not found"));
		return customer;
	}

}
