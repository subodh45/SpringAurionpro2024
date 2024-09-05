package com.aurionpro.Lending.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="loans")
@Entity
public class Loan {

	@Id
	@Column(name ="loanId" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	
	@Column(name="loanAmount")
	private int loanAmount;
	
	@Column(name="interestRate")
	private double interestRate;
	
	@Column(name="loanTerm")
	private int loanTerm;
	
	@Column(name = "startDate")
	private LocalDate startDate;
	
	@Column(name="endDate")
	private LocalDate endDate;
	
	@Column(name = "loanStatus")
	@Enumerated(EnumType.STRING)
	private loanstatus loanStatus;
	
	public Loan()
	{
		
	}
	
	public Loan(int loanId, int loanAmount, double interestRate, int loanTerm, LocalDate startDate, LocalDate endDate,
			loanstatus loanStatus) {
		super();
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.startDate = startDate;
		this.endDate = endDate;
		this.loanStatus = loanStatus;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public loanstatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(loanstatus loanStatus) {
		this.loanStatus = loanStatus;
	}
	
	
	
}
