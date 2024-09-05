 package com.aurionpro.Lending.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.aurionpro.Lending.entity.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class LoanRepoImplementation implements LoanRepository {

	@Autowired
	private EntityManager manager ;
	
	@Override
	public List<Loan> getAllLoan() {
		
		TypedQuery<Loan> query = manager.createQuery("select l from Loan as l",Loan.class);
		return query.getResultList();
	}

	@Override
	public Loan findLoan(int loanId) {
		
		return manager.find(Loan.class,loanId);
	}

	@Transactional
	@Override
	public void addLoan(Loan loan) {
		
		manager.persist(loan);	
	}

	@Transactional
	@Override
	public void updateLoan(Loan loan) {
		manager.merge(loan);		
	}

}
