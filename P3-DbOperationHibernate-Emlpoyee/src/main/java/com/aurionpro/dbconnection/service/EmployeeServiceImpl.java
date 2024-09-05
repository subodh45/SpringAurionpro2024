package com.aurionpro.dbconnection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aurionpro.dbconnection.entity.Employee;
import com.aurionpro.dbconnection.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepo.getAllEmployees();
	}

	@Override
	public Employee getEmployee(int empid) {
		
		return employeeRepo.getEmployee(empid);
	}

	@Override
	public void addEmployee(Employee employee) {
	
		employeeRepo.addEmployee(employee);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		
		employeeRepo.updateEmployee(employee);
		
	}

}
