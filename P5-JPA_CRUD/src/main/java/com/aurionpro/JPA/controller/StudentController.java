package com.aurionpro.JPA.controller;

import java.util.List;
import java.util.Optional;

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

import com.aurionpro.JPA.entity.Student;
import com.aurionpro.JPA.service.StudentService;

@RestController
@RequestMapping("/studentapp")
public class StudentController {
  
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent()
	{
		Student student = new Student();
				
		return ResponseEntity.ok(studentService.getAllStudent());
	}
	
	@GetMapping("/students/{rollno}")
	public ResponseEntity<Optional<Student>> getStudent(@PathVariable int rollno)
	{
						
		return ResponseEntity.ok(studentService.getStudent(rollno));
	}
	
	
	
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student)
	{
		studentService.addStudent(student);
		return "Student addedd successfully.";
	}

	@PutMapping("/students")
	public String updateStudent(@RequestBody Student student)
	{
		studentService.updatestudent(student);;
		return "Student updated successfully.";
	}
	
	@GetMapping("/students-name")
	public ResponseEntity<Student> getstudentsbyName(@RequestParam String name)
	{	
		return ResponseEntity.ok(studentService.getStudentByName(name));
	}
	
	@GetMapping("/students-name2")
	public ResponseEntity<List<Student>> getAllstudentsbyName(@RequestParam String name)
	{	
		return ResponseEntity.ok(studentService.getAllStudentByName(name));
	}
}
