package com.aurionpro.dbconnection.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dbconnection.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentRepositoryimpl implements StudentRepository{

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Student> getAllStudents() {
		
		TypedQuery<Student> query = manager.createQuery("select s from Student as s" ,Student.class);
		
		return query.getResultList();
	}

	@Transactional
	@Override
	public void addStudent(Student student) {
		
		manager.persist(student);
		
	}

	@Override
	public Student findStudent(int rollno) {
		
		return manager.find(Student.class, rollno);
	}

	@Transactional
	@Override
	public void updateStudent(Student student) {
		
		manager.merge(student);
		
	}

	@Override
	public List<Student> findStudentName(String name) {
		
		TypedQuery<Student> query = manager.createQuery("select s from Student as s where s.name=:theName" ,Student.class);
		query.setParameter("theName", name);
		return query.getResultList();
	}

	
}
