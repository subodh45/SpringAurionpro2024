package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.SalaryAccountDto;
import com.aurionpro.main.service.SalaryAccountService;

@RestController
@RequestMapping("/cashmanagementapp")
public class SalaryAccountController {

	@Autowired
	private SalaryAccountService salaryAccountService;
	
	@PostMapping("/salaryaccounts")
	public ResponseEntity<SalaryAccountDto> addSalaryAccountDto(@RequestBody SalaryAccountDto salaryAccountDto)
	{
		return ResponseEntity.ok(salaryAccountService.addSalaryAccountDto(salaryAccountDto));
	}
	
	@GetMapping("/salaryaccounts")
	public ResponseEntity<List<SalaryAccountDto>> getAllSalaryAccountDto()
	{
		return ResponseEntity.ok(salaryAccountService.getAllSalaryAccount());
	}
	
	@GetMapping("/salaryaccounts/{accountNumber}")
	public ResponseEntity<SalaryAccountDto> getSalaryAccountDtobyId(@PathVariable int accountNumber)
	{
		return ResponseEntity.ok(salaryAccountService.getSalaryAccountDtByAccountNumber(accountNumber));
	}
}
