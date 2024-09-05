package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.BankDto;

public interface BankService {

	BankDto addBankDto(BankDto bankDto); 
	
	List<BankDto> getAllbankDto();
	
	BankDto getBankDtobyId(int id);
	
	BankDto assignSalaryAccountToBank(int bankId , List<Integer>accountNumbers);
}

