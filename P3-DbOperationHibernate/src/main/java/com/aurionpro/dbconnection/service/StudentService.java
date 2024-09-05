package com.aurionpro.dbconnection.service;

import java.util.List;

import com.aurionpro.dbconnection.entity.Student;

public interface StudentService {

	List<Student>getAllStudents();
	Student findStudent(int rollno);
	void addstudent(Student student);
	void updateStudent(Student student);
	List<Student> findStudentName(String name);
}
