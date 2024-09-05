package com.aurionpro.main.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aurionpro.main.dto.InstructorDto;
import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.StudentDto;
import com.aurionpro.main.entity.Instructor;
import com.aurionpro.main.entity.Student;

public interface InstructorService {

	InstructorDto addInstructor(InstructorDto instructorDto);
	
	List<InstructorDto> getAllInstructor();
	
	InstructorDto getInstructorDtoById(int id);
	
	Instructor allocateCoursetoInstructor(int instructorId , List<Integer> courseIds);
	
	PageResponseDto<InstructorDto> getAllInstructorPage(int pageNumber,int pageSize);
	
	List<StudentDto> getAllStudentUnderInstructor(int instructorId);
	
}
