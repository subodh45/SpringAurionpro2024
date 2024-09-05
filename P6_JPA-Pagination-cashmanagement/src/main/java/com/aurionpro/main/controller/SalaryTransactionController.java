package com.aurionpro.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.entity.SalaryTransaction;
import com.aurionpro.main.service.SalaryTransactionService;

@RestController
@RequestMapping("/cashmanagementapp")
public class SalaryTransactionController {

	@Autowired
	private SalaryTransactionService salaryTransactionService;
	
	@GetMapping("salariesTransaction")
	public ResponseEntity<Page<SalaryTransaction>> getAllSalaryTransaction(@RequestParam int pageSize , @RequestParam int pageNumber)
	{
		return ResponseEntity.ok(salaryTransactionService.getAllSalaryTransaction(pageSize, pageNumber));
	}
	
	@PostMapping("salariesTransaction")
	public ResponseEntity<SalaryTransaction>  addSalaryTransaction(@RequestBody SalaryTransaction salaryTransaction)
	{
	   return ResponseEntity.ok(salaryTransactionService.addSalaryTransaction(salaryTransaction));	
	}
	
	@PutMapping("salariesTransaction")
	public ResponseEntity<SalaryTransaction>  updateSalaryTransaction(@RequestBody SalaryTransaction salaryTransaction)
	{
	   return ResponseEntity.ok(salaryTransactionService.updateSalaryTransaction(salaryTransaction));	
	}
}
