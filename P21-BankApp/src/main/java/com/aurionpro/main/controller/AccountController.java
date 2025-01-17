package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.AccountDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/accounts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AccountDto> addAccount(@Valid @RequestBody AccountDto accountDto)
	{
		return ResponseEntity.ok(accountService.addAccount(accountDto));
	}
	
	@GetMapping("/accounts")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AccountDto>> getAllAccount()
	{
		return ResponseEntity.ok(accountService.getAllAccounts());
	}
	
	@GetMapping("/accounts/{accountNumber}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AccountDto> getAccountByAccountNumber(@Valid @PathVariable long accountNumber)
	{
		return ResponseEntity.ok(accountService.getAccountbyAccountNumber(accountNumber));
	}
	
	@GetMapping("/accounts/transactions/{accountNumber}")
	public ResponseEntity<List<TransactionDto>> getAccountTransaction(@Valid @PathVariable long accountNumber)
	{
		return ResponseEntity.ok(accountService.getAccountTransaction(accountNumber));
	}
	
	
	@GetMapping("/accounts/transactions/page")
	public ResponseEntity<PageResponseDto<TransactionDto>> getAccountTransactionPage(@Valid @RequestParam long accountNumber,@RequestParam int pageNumber,@RequestParam int pageSize)
	{
		return ResponseEntity.ok(accountService.getAccountTransaction2(accountNumber, pageNumber, pageSize));
	}
	
	
}
