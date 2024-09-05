package com.aurionpro.main.dto;

import java.time.LocalDate;

import com.aurionpro.main.entity.SalaryAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class EmployeeDto {

    private int employeeId;

	private String firstName;

	private String lastName;

	private String phoneNumber;
	
	private String email;

	private String position;

	private LocalDate hireDate;

	private double salary;

	private SalaryAccount salaryAccount;

	private String status;
	
}
