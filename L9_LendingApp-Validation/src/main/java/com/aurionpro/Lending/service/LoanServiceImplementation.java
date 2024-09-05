package com.aurionpro.Lending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.Lending.entity.Loan;
import com.aurionpro.Lending.repository.LoanRepository;

import jakarta.transaction.Transactional;

@Service
public class LoanServiceImplementation  implements LoanService{

	@Autowired
	private LoanRepository loanRepo ;
	
	@Override
	public List<Loan> getAllLoan() {
		
		return loanRepo.getAllLoan();
	}

	@Override
	public Loan findLoan(int loanId) {
		
		return loanRepo.findLoan(loanId);
	}

	@Transactional
	@Override
	public void addLoan(Loan loan) {
		
		loanRepo.addLoan(loan);
	}

	@Transactional
	@Override
	public void updateLoan(Loan loan) {
		// TODO Auto-generated method stub
		loanRepo.updateLoan(loan);
	}

}
