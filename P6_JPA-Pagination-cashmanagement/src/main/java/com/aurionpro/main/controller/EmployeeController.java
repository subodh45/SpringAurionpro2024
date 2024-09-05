package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.EmployeeDto;
import com.aurionpro.main.entity.Employee;
import com.aurionpro.main.entity.SalaryAccount;
import com.aurionpro.main.service.EmployeeService;

@RestController
@RequestMapping("/cashmanagementapp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("employees")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return employeeService.addEmployee(employee);
		
	}
	
	@PostMapping("employees/dtos")
	public ResponseEntity<EmployeeDto> addEmployeeDto(@RequestBody EmployeeDto employeeDto)
	{
		return ResponseEntity.ok(employeeService.addEmployeeDto(employeeDto));
				
	}
	
	
	
	@PutMapping("employees")
	public String updateEmployee(@RequestBody Employee employee)
	{
		employeeService.updateEmployee(employee);
		return "Employee updated  Successfully.";
	}
	
	@GetMapping("employees")
	public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam(required = false) String name,@RequestParam int pageSize ,@RequestParam int pageNumber)
	{
		if(name !=null)
		   return  ResponseEntity.ok( employeeService.getAllEmployeebyfirstName(name, pageSize, pageNumber));
		
		return  ResponseEntity.ok( employeeService.getAllEmployee(pageSize, pageNumber));

	}
	
	@GetMapping("employees/salaryAccount/{employeeId}")
	public ResponseEntity<SalaryAccount> getEmployeeSalaryAccount(@PathVariable int employeeId)
	{
		return ResponseEntity.ok(employeeService.getEmployeeSalaryAccount(employeeId));
	}
	
	
	@PutMapping("employees/updateSalaryAccount")
	public ResponseEntity<Employee> updateEmployeeSalaryAccount(@RequestParam int employeeId , @RequestBody SalaryAccount salaryAccount)
	{
		return  ResponseEntity.ok(employeeService.updateEmployeeSalaryAccount(employeeId, salaryAccount));
	}
	
	@GetMapping("employees/dtos")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeeDto()
	{
		return ResponseEntity.ok(employeeService.getAllEmployeeDto());
	}
	
	@GetMapping("employees/dtos/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeDtobyId(@PathVariable int id)
	{
		return ResponseEntity.ok(employeeService.getEmployeeDtobyId(id));
	}
	
	@PutMapping("employees/clients")
	public ResponseEntity<Employee> assignClient(@RequestParam int employeeId, @RequestParam int clientId)
	{
		return ResponseEntity.ok(employeeService.assignClient(employeeId, clientId));
	}
	
	
	
}
