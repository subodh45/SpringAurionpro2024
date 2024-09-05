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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name="loans")
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Loan {

	@Id
	@Column(name ="loanId" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;
	
	@NotNull(message = "Loan amount cannot be null")
	//@Pattern(regexp="(^$|[0-9]{10})" ,message = "Amount must be in number")
	@Min(value = 1, message = "Loan amount must be at least 1")
	@Column(name="loanAmount")
	private int loanAmount;
	
	@DecimalMin(value = "0.0", inclusive = false, message = "Interest rate must be greater than 0")
	@Column(name="interestRate")
	private double interestRate;
	
	@Min(value = 1, message = "Loan term must be at least 1 month")
	@Column(name="loanTerm")
	private int loanTerm;
	
	@PastOrPresent(message = "Start date cannot be in the future")
	@Column(name = "startDate")
	private LocalDate startDate;
	
	
	@Column(name="endDate")
	private LocalDate endDate;
	
	@NotNull(message = "Loan status cannot be null")
	@Column(name = "loanStatus")
	@Enumerated(EnumType.STRING)
	private loanstatus loanStatus;
	

}
