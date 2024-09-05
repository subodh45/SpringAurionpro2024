package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.StudentDto;
import com.aurionpro.main.entity.Address;
import com.aurionpro.main.entity.Student;
import com.aurionpro.main.service.StudentService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/advancemappingapp")
public class StudentController {
   
	@Autowired
	private StudentService studentServcie;
	
	@PostMapping("/students")
	public ResponseEntity<StudentDto>  addStudent(@RequestBody Student student)
	{
		return ResponseEntity.ok( studentServcie.addStudent(student));
	}
	
	@GetMapping("/students")
	public ResponseEntity<Page<Student>> getAllStudent(@RequestParam int pageNumber, @RequestParam int pageSize)
	{
		return ResponseEntity.ok(studentServcie.getAllStudent(pageNumber, pageSize));
	}
	
	@GetMapping("/studentdtos")
	public ResponseEntity<PageResponseDto<StudentDto>> getAllStudents(@RequestParam int pageNumber , @RequestParam int pageSize)
	{
		return ResponseEntity.ok(studentServcie.getAllStudents(pageNumber, pageSize));
	}
	
	@GetMapping("/students/{rollno}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable int rollno)
	{
		return ResponseEntity.ok(studentServcie.findStudent(rollno));
	}
	
	@GetMapping("/students/address/{rollno}")
	public ResponseEntity<Address> getstudentddress(@PathVariable int rollno)
	{
		return ResponseEntity.ok(studentServcie.findStudentAddress(rollno));
	}
	
	@PutMapping("/students/address2")
	public ResponseEntity<Student> updateAddress(@RequestParam int rollno , @RequestParam  String buildingName,@RequestParam   String areaName, @RequestParam  String city ,@RequestParam  int pincode ) {
			
		return ResponseEntity.ok(studentServcie.updateStudentAddress(rollno, buildingName, areaName, city, pincode));
	}
	
	@PutMapping("/students/address")
	public ResponseEntity<Student> updateAddress2(@RequestParam int rollno , @RequestBody  Address address ) {
			
		return ResponseEntity.ok(studentServcie.updateStudentAddress2(rollno, address ));
	}
	
	@PutMapping("/students/courses")
	public ResponseEntity<StudentDto> assignCourse(@RequestParam int rollno , @RequestBody List<Integer> courseIds)
	{
		return ResponseEntity.ok(studentServcie.assigncourses(rollno, courseIds));
	}
}
