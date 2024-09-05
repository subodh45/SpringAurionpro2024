package com.aurionpro.main.service;

import org.springframework.data.domain.Page;

import com.aurionpro.main.entity.SalaryTransaction;

public interface SalaryTransactionService {

	Page<SalaryTransaction> getAllSalaryTransaction(int pageSize,int pageNumber);
	
	SalaryTransaction addSalaryTransaction(SalaryTransaction  salaryTransaction); 
	
	SalaryTransaction updateSalaryTransaction(SalaryTransaction salaryTransaction);
}
