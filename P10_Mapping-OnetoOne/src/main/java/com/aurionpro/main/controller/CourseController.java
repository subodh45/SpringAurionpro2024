package com.aurionpro.main.controller;

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

import com.aurionpro.main.dto.CourseDto;
import com.aurionpro.main.dto.InstructorDto;
import com.aurionpro.main.dto.StudentDto;
import com.aurionpro.main.entity.Course;
import com.aurionpro.main.service.CourseService;

@RestController
@RequestMapping("/advancemappingapp")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	
	@PostMapping("/courses")
	public ResponseEntity<CourseDto> addNewCorseDto(@RequestBody CourseDto courseDto)
	{
		return ResponseEntity.ok(courseService.addNewCourse(courseDto));
	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<CourseDto>> getAllCourseDto()
	{
		return ResponseEntity.ok(courseService.getAllCourse());
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<CourseDto> getCourseDtobyId(@PathVariable int id)
	{
		return ResponseEntity.ok(courseService.getCourseDtoById(id));
	}
	
	@PutMapping("/courses/instructors")
	public ResponseEntity<Course> updateInstructor(@RequestParam int courseId ,@RequestParam int instructorId )
	{
		return ResponseEntity.ok(courseService.updateInstructor(courseId, instructorId));
	}
	
	@PutMapping("/courses/students")
	public ResponseEntity<CourseDto> assignCourse(@RequestParam int courseId , @RequestBody List<Integer> studentIds)
	{
		return ResponseEntity.ok(courseService.addStudentToCourse(courseId, studentIds));
	}
}

