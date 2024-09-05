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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dbconnection.entity.Student;
import com.aurionpro.dbconnection.service.StudentService;

@RestController
@RequestMapping("/StudentApp")
public class StudentController {

	@Autowired
	private StudentService studentservice ;
	
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		
		return ResponseEntity.ok(studentservice.getAllStudents());
	}
	
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student)
	{
	  studentservice.addstudent(student);
	  return "record Added";	
	}
	
	@GetMapping("/students/{rollno}")
	public ResponseEntity<Student> findStudent(@PathVariable int rollno)
	{
		return ResponseEntity.ok( studentservice.findStudent(rollno));
	}
	
	@PutMapping("/students")
	public String updateStudent(@RequestBody Student student)
	{ 
	  studentservice.updateStudent(student);
	  return "Student Updated Successfully.";	
	}
	
	@GetMapping("/students-name")
	public ResponseEntity<List<Student>> getstudentsbyName(@RequestParam String name)
	{	
		return ResponseEntity.ok(studentservice.findStudentName(name));
	}
	
	
}
