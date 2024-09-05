package com.aurionpro.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.entity.Customers;
import com.aurionpro.main.services.CustomerServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("validation")
public class CustomerController {

	@Autowired
	private CustomerServices customerservice;
	
	@PostMapping("/customers")
	public String addCustomer(@Valid @RequestBody Customers customer)
	{
		customerservice.addCustomer(customer);
		return "added successfully";
	}
}
