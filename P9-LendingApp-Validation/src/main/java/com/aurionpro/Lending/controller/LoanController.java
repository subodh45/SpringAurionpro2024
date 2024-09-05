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

import com.aurionpro.Lending.entity.Loan;
import com.aurionpro.Lending.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lendingapp")
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	
	@GetMapping("/loans")
	public ResponseEntity<List<Loan>> getAllLoan()
	{
		return ResponseEntity.ok( loanService.getAllLoan());
	}
	
	@GetMapping("/loans/{loanId}")
	public ResponseEntity<Loan> findLoan(@PathVariable int loanId)
	{
		return ResponseEntity.ok( loanService.findLoan(loanId));
	}
	
	@PostMapping("/loans")
	public String addLoan(@Valid @RequestBody Loan loan)
	{
		loanService.addLoan(loan);		
		return "loan added succefuuly";
	}
	
	@PutMapping("/loans")
	public String updateLoan(@RequestBody Loan loan)
	{
		loanService.updateLoan(loan);
		return "Loan updated Succeffully";
	}
}
