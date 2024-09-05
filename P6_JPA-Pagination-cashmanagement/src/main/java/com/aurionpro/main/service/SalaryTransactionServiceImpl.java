package com.aurionpro.main.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.main.entity.SalaryTransaction;
import com.aurionpro.main.repository.SalaryTransactionRepo;

@Service
public class SalaryTransactionServiceImpl implements SalaryTransactionService {

	private SalaryTransactionRepo salaryTransactionRepo;
	
	@Override
	public Page<SalaryTransaction> getAllSalaryTransaction(int pageSize, int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return salaryTransactionRepo.findAll(pageable);
	}

	@Override
	public SalaryTransaction addSalaryTransaction(SalaryTransaction salaryTransaction) {
		
		return salaryTransactionRepo.save(salaryTransaction);
	}

	@Override
	public SalaryTransaction updateSalaryTransaction(SalaryTransaction salaryTransaction) {
		
		return salaryTransactionRepo.save(salaryTransaction);
	}

}
