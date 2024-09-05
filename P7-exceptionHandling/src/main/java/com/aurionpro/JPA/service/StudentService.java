package com.aurionpro.JPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.JPA.dto.PageResponseDto;

import com.aurionpro.JPA.entity.Student;

public interface StudentService {
	
	PageResponseDto<Student>  getAllStudents(int pageSize,int pageNumber);
	
	PageResponseDto<Student> getAllStudentsbyName(String name ,int pageSize,int pageNumber);
	
	Student getstudentbyrollno(int rollno);
	
}
