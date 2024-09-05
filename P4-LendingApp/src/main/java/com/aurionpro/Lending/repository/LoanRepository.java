package com.aurionpro.Lending.repository;

import java.util.List;

import com.aurionpro.Lending.entity.Customer;
import com.aurionpro.Lending.entity.Loan;

public interface LoanRepository {

	List<Loan> getAllLoan();
	
	Loan findLoan(int loanId );
	
	void addLoan(Loan loan);
	
	void updateLoan(Loan loan);
	
	
}
