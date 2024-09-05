package com.aurionpro.Lending.service;

import java.util.List;

import com.aurionpro.Lending.entity.Loan;

public interface LoanService {

    List<Loan> getAllLoan();
	
	Loan findLoan(int loanId );
	
	void addLoan(Loan loan);
	
	void updateLoan(Loan loan);
}
