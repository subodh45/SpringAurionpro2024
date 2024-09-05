package com.aurionpro.main.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="salary")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="salaryId")
	private int salaryId;
	
	@Column(name="salaryMonth") //want month on which salary is credited
	private int  salaryMonth;
	
	@Column(name="grossSalary")
	private double grossSalary;
	
	@Column(name="deductions")
	private double deductions;
	
	@Column(name="netSalary")
	private double netSalary;
	
	@Column(name="paymentDate")   //date on which salary is credited
	private LocalDate paymentDate;
	
	@Column(name="status")
	private String status;
}
