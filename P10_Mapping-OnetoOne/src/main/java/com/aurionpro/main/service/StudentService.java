package com.aurionpro.main.service;



import java.util.List;

import org.springframework.data.domain.Page;

import com.aurionpro.main.dto.PageResponseDto;
import com.aurionpro.main.dto.StudentDto;
import com.aurionpro.main.entity.Address;
import com.aurionpro.main.entity.Student;

public interface StudentService {

	 StudentDto addStudent(Student student);
	
	 Page<Student> getAllStudent(int pageNumber,int pageSize);
	 
	 Address findStudentAddress(int rollno);
	 
	 StudentDto findStudent(int rollno);
	 
	 Student updateStudentAddress(int rollno,String buildingName,  String areaName, String city , int pincode);

	 Student updateStudentAddress2(int rollno,Address address);
	 
	 PageResponseDto<StudentDto> getAllStudents(int pageNumber, int pageSize);
	 
	 StudentDto assigncourses(int rollno, List<Integer> courseIds);
}
