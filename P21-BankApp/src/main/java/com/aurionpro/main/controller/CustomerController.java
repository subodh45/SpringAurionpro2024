package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.CustomerDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PageResponseDto<CustomerDto>> getAllCustomer(@RequestParam int pageNumber,@RequestParam int pageSize)
	{
		return ResponseEntity.ok(customerService.getAllCustomers(pageNumber , pageSize));
	}
	
//	@GetMapping("/customers")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<List<CustomerDto>> getAllCustomer()
//	{
//		return ResponseEntity.ok(customerService.getAllCustomerDto());
//	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customers/{email}")
	public ResponseEntity<CustomerDto> getCustomerByEmail(@Valid @PathVariable String email)
	{
		return ResponseEntity.ok(customerService.getCustomerbyEmail(email));
	}
	
	@PutMapping("/customers")
	public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto)
	{
		return ResponseEntity.ok(customerService.updateCustomer(customerDto));
	}
	
	@GetMapping("/customers/accounts/{customerId}")
	public ResponseEntity<List<AccountDto>> getCustomerAccounts(@Valid @PathVariable int customerId)
	{
		return ResponseEntity.ok(customerService.getAccountsOfCustomerId(customerId));
	}
	
	@PostMapping("/customers/accounts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AccountDto> createCustomerAccount(@Valid @RequestBody AccountDto accountDto)
	{
		return ResponseEntity.ok(customerService.createCustomerAccount(accountDto));
	}
	
	
//	@PostMapping("/customers/register")
//	public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto)
//	{
//		return ResponseEntity.ok(customerService.addCustomerDto(customerDto));
//	}
//	
//	@GetMapping("/customers/login")
//	public ResponseEntity<CustomerDto> customerLogin(@Valid @RequestBody CustomerDto customerDto)
//	{
//		return ResponseEntity.ok(customerService.customerLogin(customerDto));
//	}
}
