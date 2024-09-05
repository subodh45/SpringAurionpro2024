package com.aurionpro.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="salaryAccount")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SalaryAccount {

	@Id
	@Column(name = "accountNumber")
	private int accountNumber;
	
	@Column(name="accountHolderName")
	private String accountHolderName;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH,CascadeType.PERSIST})
	@JoinColumn(name="bankId")
	private Bank bank;
	
}
