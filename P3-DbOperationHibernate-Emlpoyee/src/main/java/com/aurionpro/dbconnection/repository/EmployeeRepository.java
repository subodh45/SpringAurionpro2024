package com.aurionpro.dbconnection.repository;

import java.util.List;

import com.aurionpro.dbconnection.entity.Employee;

public interface EmployeeRepository {

	List<Employee> getAllEmployees();
	Employee getEmployee(int empId);
	void addEmployee(Employee employee);
	void updateEmployee(Employee employee);
}
