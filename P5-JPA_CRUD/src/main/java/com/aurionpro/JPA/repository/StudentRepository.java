package com.aurionpro.JPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.JPA.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	
    Student findByName(String name);
    
    List<Student> getAllStudentByName(String name);
}
