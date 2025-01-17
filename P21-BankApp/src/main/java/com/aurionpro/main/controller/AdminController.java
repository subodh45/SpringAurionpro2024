package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.Repository.AccountRepository;
import com.aurionpro.main.Repository.CustomerRepository;
import com.aurionpro.main.Repository.TransactionRepository;
import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.CustomerDto;
import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.entity.Admin;
import com.aurionpro.main.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admins")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestParam String adminName ,@RequestParam String password)
	{
		return ResponseEntity.ok(adminService.addAdmin(adminName, password));
	}
	
	@GetMapping("admins/transactions")
	 public ResponseEntity<List<TransactionDto>> getAllTransaction()
	 {
		 return ResponseEntity.ok(adminService.getAllTransaction());
	 }
	
	@PostMapping("admins/accounts")
	 public ResponseEntity<AccountDto> addAccount(@Valid @RequestBody AccountDto accounDto)
	 {
		 return ResponseEntity.ok(adminService.addAccount(accounDto));
	 }
	
	@GetMapping("admins/accounts")
	public ResponseEntity<List<AccountDto>> getAllAccounts()
	{
		return ResponseEntity.ok(adminService.getAllAccounts()) ;
	}
	
	@GetMapping("admins/customers")
	public ResponseEntity<List<CustomerDto>> getAllCustomerDto()
	{
		return ResponseEntity.ok(adminService.getAllCustomerDto()) ;
	}
	
//	@PostMapping("admins/customers")
//	public ResponseEntity<CustomerDto> addCustomerDto(@Valid @RequestBody CustomerDto customerDto)
//	{
//		return  ResponseEntity.ok(adminService.addCustomerDto(customerDto));
//	}
}
