package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.BankDto;
import com.aurionpro.main.dto.SalaryAccountDto;

public interface SalaryAccountService {

	SalaryAccountDto addSalaryAccountDto(SalaryAccountDto salaryAccountDto);
	
	List<SalaryAccountDto> getAllSalaryAccount();
	
	SalaryAccountDto getSalaryAccountDtByAccountNumber(int accountNumber);
	
}
