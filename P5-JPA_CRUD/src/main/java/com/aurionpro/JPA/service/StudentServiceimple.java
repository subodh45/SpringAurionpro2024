package com.aurionpro.JPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.JPA.entity.Student;
import com.aurionpro.JPA.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceimple implements StudentService {
  
	@Autowired
	private StudentRepository studentRepository;
	
	
	@Override
	@Transactional
	public List<Student> getAllStudent() {
		
		return studentRepository.findAll();
	}


	@Override
	@Transactional
	public Optional<Student> getStudent(int rollno) {
		
		return studentRepository.findById(rollno);
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		
		studentRepository.save(student);
	}


	@Override
	@Transactional
	public void updatestudent(Student student) {
		
		studentRepository.save(student);
	}


	@Override
	@Transactional
	public Student getStudentByName(String name) {
		
		return  studentRepository.findByName(name);
	}


	@Override
	@Transactional
	public List<Student> getAllStudentByName(String name) {
		
		return studentRepository.getAllStudentByName(name);
	}


	


	

}
