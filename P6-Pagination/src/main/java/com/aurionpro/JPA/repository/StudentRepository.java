package com.aurionpro.JPA.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.JPA.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
  
	Page findByname(String name,Pageable page);
        
}
