package com.aurionpro.Lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.Lending.entity.Customer;
import com.aurionpro.Lending.service.CustomerService;

@RestController
@RequestMapping("/lendingapp")
public class customerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer()
	{
		return ResponseEntity.ok(customerService.getAllCustomer());
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> findCustomer(@PathVariable int customerId )
	{
		return  ResponseEntity.ok( customerService.findCustomer(customerId));
	}
	
	@PostMapping("/customers")
	public String addCustomer(@RequestBody Customer customer)
	{
		customerService.addcustomer(customer);
		return "Customer Added successfully";
	}
	
	
	@PutMapping("/customers")
	public String updateCustomer(@RequestBody Customer customer)
	{
		customerService.updateCustomer(customer);
		return "Customer updated successfully.";
	}
}
