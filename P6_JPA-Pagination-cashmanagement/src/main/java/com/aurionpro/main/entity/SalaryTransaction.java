package com.aurionpro.main.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="salaryTransaction")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SalaryTransaction {

	@Id
	@Column(name="transactionId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@PastOrPresent
	@Column(name="transactionDate")
	private LocalDate transactionDate;
	@Column(name="amount")
	private double amount ;
	@Column(name="status")
	private String status;
}
