package com.aurionpro.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.main.entity.Customers;
import com.aurionpro.main.repository.CustomerRepository;

@Service
public class CustomerServiceImple implements CustomerServices {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public void addCustomer(Customers customer) {
		
		customerRepo.save(customer);
		
	}

}
