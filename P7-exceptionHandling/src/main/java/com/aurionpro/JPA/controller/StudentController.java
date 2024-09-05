package com.aurionpro.JPA.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.JPA.dto.PageResponseDto;
import com.aurionpro.JPA.entity.Student;
import com.aurionpro.JPA.errors.StudentErrorResponse;
import com.aurionpro.JPA.exception.StudentDoesNotExistsException;
import com.aurionpro.JPA.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {
  
	@Autowired
	private StudentService studentService;
			
	@GetMapping("/students")
	public ResponseEntity<PageResponseDto<Student>> getAllStudents(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam(required = false) String name )
	{			
		if(name!=null)
			return ResponseEntity.ok(studentService.getAllStudentsbyName(name, pageSize, pageNumber));
		
		   return ResponseEntity.ok(studentService.getAllStudents(pageSize, pageNumber));			
	}
	
	@GetMapping("/students/{rollno}")
	public ResponseEntity<Student> getStudentByrollno(@PathVariable int rollno )
	{			
		   return ResponseEntity.ok(studentService.getstudentbyrollno(rollno));			
	}
	
	
}
