package com.aurionpro.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.entity.Salary;
import com.aurionpro.main.service.SalaryService;

@RestController
@RequestMapping("/cashmanagementapp")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;
	
	@GetMapping("salaries")
	public ResponseEntity<Page<Salary>> getAllSalary(@RequestParam int pageNumber , @RequestParam int pageSize)
	{
		return ResponseEntity.ok(salaryService.getAllSalary(pageNumber, pageSize));
	}
	
	@PostMapping("salaries")
	public ResponseEntity<Salary>  addSalary(@RequestBody Salary salary)
	{
	   return ResponseEntity.ok(salaryService.addSalary(salary));	
	}
	
	@PutMapping("salaries")
	public ResponseEntity<Salary>  updateSalary(@RequestBody Salary salary)
	{
	   return ResponseEntity.ok(salaryService.updateSalary(salary));	
	}
}
