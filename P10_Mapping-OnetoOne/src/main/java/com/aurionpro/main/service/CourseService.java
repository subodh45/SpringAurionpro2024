package com.aurionpro.main.service;

import java.util.List;

import com.aurionpro.main.dto.CourseDto;
import com.aurionpro.main.dto.InstructorDto;
import com.aurionpro.main.entity.Course;

public interface CourseService {

	CourseDto addNewCourse(CourseDto courseDto);
	
    List<CourseDto> getAllCourse();
	
	CourseDto getCourseDtoById(int id);
	
	Course updateInstructor(int courseId , int instructorId);
	
	CourseDto addStudentToCourse(int course, List<Integer> studentIds);
	
}
