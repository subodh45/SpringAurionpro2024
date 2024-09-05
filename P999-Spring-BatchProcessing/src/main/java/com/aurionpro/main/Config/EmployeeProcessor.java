package com.aurionpro.main.Config;

import org.springframework.batch.item.ItemProcessor;

import com.aurionpro.main.Entity.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(Employee item) throws Exception {
		
		return item;
	}

}
