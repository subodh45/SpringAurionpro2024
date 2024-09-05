package com.aurionpro.dbconnection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dbconnection.entity.Student;
import com.aurionpro.dbconnection.repository.StudentRepository;
import com.aurionpro.dbconnection.repository.StudentRepositoryimpl;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository Studentrepo ;
	
	@Override
	public List<Student> getAllStudents() {
		
		
		return Studentrepo.getAllStudents();
	}

	@Override
	public void addstudent(Student student) {
		// TODO Auto-generated method stub
		Studentrepo.addStudent(student);
	}

	@Override
	public Student findStudent(int rollno) {
		
		return Studentrepo.findStudent(rollno);
	}

	@Override
	public void updateStudent(Student student) {
		
		Studentrepo.updateStudent(student);
		
	}

	@Override
	public List<Student> findStudentName(String name) {
		
		return Studentrepo.findStudentName(name);
	}

}
