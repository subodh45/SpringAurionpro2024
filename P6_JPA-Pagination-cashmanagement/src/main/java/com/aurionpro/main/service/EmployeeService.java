package com.aurionpro.main.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aurionpro.main.dto.EmployeeDto;
import com.aurionpro.main.entity.Employee;
import com.aurionpro.main.entity.SalaryAccount;

public interface EmployeeService {
    //get employee
	Employee addEmployee(Employee employee);
	//get all employee
	Page<Employee> getAllEmployee(int pageSize,int pageNumber);	
	//get employee salary account
	SalaryAccount  getEmployeeSalaryAccount(int employeeId);
	//get employee by first name
	Page<Employee> getAllEmployeebyfirstName(String name ,int pageSize,int pageNumber);
	
	//update employee
    void updateEmployee(Employee employee);
	//update salary account of employee   
    Employee updateEmployeeSalaryAccount(int employeeid,SalaryAccount salaryAccount);
    
    //add employeeDto
    EmployeeDto addEmployeeDto(EmployeeDto employeeDto);
    
    //get AllEmployeeDto
    
    List<EmployeeDto> getAllEmployeeDto();
    
    //get EMployeeDto by Id(primaryKey)
    
    EmployeeDto getEmployeeDtobyId(int Id);
    
    //assign client to employee 
    
    Employee assignClient(int employeedId, int clientId);
    
}
