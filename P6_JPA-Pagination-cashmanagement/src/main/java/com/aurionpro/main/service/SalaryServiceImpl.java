package com.aurionpro.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.main.entity.Salary;
import com.aurionpro.main.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService{

	@Autowired
	private SalaryRepository salaryRepo;
	
	@Override
	public Page<Salary> getAllSalary(int pageNumber,int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		return salaryRepo.findAll(pageable);
	}

	@Override
	public Salary addSalary(Salary salary) {
		
		return salaryRepo.save(salary);
	}

	@Override
	public Salary updateSalary(Salary salary) {
		
		return salaryRepo.save(salary);
	}

}
