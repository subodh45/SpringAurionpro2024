package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.BankDto;
import com.aurionpro.main.service.BankService;

@RestController
@RequestMapping("/cashmanagementapp")
public class BankController {
    @Autowired
	private BankService bankService ;
    
    @PostMapping("banks")
    public ResponseEntity<BankDto> addBankDto(@RequestBody BankDto bankDto)
    {
    	return ResponseEntity.ok(bankService.addBankDto(bankDto));
    }
    
    @GetMapping("banks")
    public ResponseEntity<List<BankDto>> getAllBankDto()
    {
    	return ResponseEntity.ok(bankService.getAllbankDto());
    }
    
    @GetMapping("banks/{id}")
    public ResponseEntity<BankDto> getBankDtobyId(@PathVariable int  id)
    {
    	return ResponseEntity.ok(bankService.getBankDtobyId(id));
    }
    
    @PutMapping("/banks/salaryaccounts")
    public ResponseEntity<BankDto> assignSalaryAccountToBank(@RequestParam int bankId , @RequestBody List<Integer>accountNumbers)
    {
    	return ResponseEntity.ok(bankService.assignSalaryAccountToBank(bankId, accountNumbers));
    }
}
