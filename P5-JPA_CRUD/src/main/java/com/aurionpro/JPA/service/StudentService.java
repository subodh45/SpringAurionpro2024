package com.aurionpro.JPA.service;

import java.util.List;
import java.util.Optional;

import com.aurionpro.JPA.entity.Student;

public interface StudentService {

	List<Student> getAllStudent();
	
	Optional<Student> getStudent(int rollno);
	
	Student getStudentByName(String name);
	
	void addStudent(Student student);
	
	void updatestudent(Student student);
	
	List<Student> getAllStudentByName(String name);
}
