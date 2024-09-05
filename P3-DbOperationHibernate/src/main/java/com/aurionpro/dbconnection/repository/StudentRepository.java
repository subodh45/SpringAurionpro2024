package com.aurionpro.dbconnection.repository;

import java.util.List;

import com.aurionpro.dbconnection.entity.Student;

public interface StudentRepository {

	
	List<Student> getAllStudents();
    Student findStudent(int rollno);
	void addStudent(Student student);
	void updateStudent(Student student);
	List<Student> findStudentName(String name);
}
