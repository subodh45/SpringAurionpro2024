package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.entity.TransactionType;
import com.aurionpro.main.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/transactions")
	public ResponseEntity<TransactionDto> perfromTransaction(@Valid @RequestParam long  senderAccountNumber,@RequestParam long receiverAccountNumber,
			@RequestParam TransactionType transactionType,@RequestParam int amount)
	{
		return ResponseEntity.ok(transactionService.performTransaction(senderAccountNumber,receiverAccountNumber,transactionType,amount));
	}
	
	@GetMapping("/transactions")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<TransactionDto>> getAllTransaction()
	{
		return ResponseEntity.ok(transactionService.getAllTransactions());
	}
}
