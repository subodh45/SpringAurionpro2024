package com.aurionpro.Lending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.Lending.entity.Customer;
import com.aurionpro.Lending.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public List<Customer> getAllCustomer() {
		
		return customerRepo.getAllCustomer();
	}

	@Override
	public Customer findCustomer(int customerId) {
		// TODO Auto-generated method stub
		return customerRepo.findCustomer(customerId);
	}

	@Override
	public void addcustomer(Customer customer) {
		// TODO Auto-generated method stub
		 customerRepo.addCustomer(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.updatecustomer(customer);
	}

}
