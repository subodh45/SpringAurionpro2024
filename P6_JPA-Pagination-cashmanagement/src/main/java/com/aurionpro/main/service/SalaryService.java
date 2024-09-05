package com.aurionpro.main.service;

import org.springframework.data.domain.Page;

import com.aurionpro.main.entity.Salary;

public interface SalaryService {

	Page<Salary> getAllSalary(int pageNumber, int pageSize);
	
	Salary addSalary(Salary salary);
	
	Salary updateSalary(Salary salary);
}
