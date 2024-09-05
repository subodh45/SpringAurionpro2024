package com.aurionpro.dbconnection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dbconnection.entity.Employee;

import com.aurionpro.dbconnection.service.EmployeeService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/EmployeeApp")
public class EmployeeController {

	@Autowired
	 private EmployeeService employeeService;
	 
	
	 @GetMapping("/employees")
		public ResponseEntity<List<Employee>> getAllEmployees()
		{
			
			return  ResponseEntity.ok( employeeService.getAllEmployees());
		}
	 
	 @GetMapping("/employees/{empId}")
		public ResponseEntity<Employee> getEmployee(@PathVariable int empId)
		{
			
			return  ResponseEntity.ok( employeeService.getEmployee(empId));
		}
	 
	 @PostMapping("/employees")
	 public String addEmployee(@RequestBody Employee employee)
	 {
		 employeeService.addEmployee(employee);
		 return "Employee added successfully";
	 }
	 
	 @PutMapping("/employees")
	 public String updateEmployee(@RequestBody Employee employee)
	 {
		 employeeService.updateEmployee(employee);
		 return "Employee Updated Successfully.";
	 }
	 
}
