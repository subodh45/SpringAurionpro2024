package com.aurionpro.dbconnection.service;

import java.util.List;

import com.aurionpro.dbconnection.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	Employee getEmployee(int empid);
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee);
}
