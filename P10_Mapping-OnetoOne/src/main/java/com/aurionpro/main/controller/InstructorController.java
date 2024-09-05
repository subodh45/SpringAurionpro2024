package com.aurionpro.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.dto.InstructorDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.StudentDto;
import com.aurionpro.main.entity.Instructor;
import com.aurionpro.main.entity.Student;
import com.aurionpro.main.service.InstructorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/advancemappingapp")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors")
	public ResponseEntity<InstructorDto> addNewInstructorDto(@RequestBody InstructorDto instructorDto)
	{
		return ResponseEntity.ok(instructorService.addInstructor(instructorDto));
	}
	
	@GetMapping("/instructors")
	public ResponseEntity<List<InstructorDto>> getAllInstructorDto()
	{
		return ResponseEntity.ok(instructorService.getAllInstructor());
	}
	
	@GetMapping("/instructors/{id}")
	public ResponseEntity<InstructorDto> getInstructorDtobyId(@PathVariable int id)
	{
		return ResponseEntity.ok(instructorService.getInstructorDtoById(id));
	}
	
	@PutMapping("/instructors/courses")
	public ResponseEntity<Instructor> allocateCourse(@RequestParam int instructorId , @RequestBody List<Integer> courseIds)
	{
		return ResponseEntity.ok(instructorService.allocateCoursetoInstructor(instructorId, courseIds));
	}
	
	@GetMapping("/instructors/page")
	public ResponseEntity<PageResponseDto<InstructorDto>> getAllInstructorInpage(@RequestParam int pageNumber , @RequestParam int pageSize)
	{
	 return ResponseEntity.ok(instructorService.getAllInstructorPage(pageNumber, pageSize));	
	}
	
	@GetMapping("/instructors/students/{instructorId}")
	public ResponseEntity<List<StudentDto>> getAllStudentsofInstructor(@PathVariable int instructorId)
	{
		return ResponseEntity.ok(instructorService.getAllStudentUnderInstructor(instructorId));
	}
	
}
