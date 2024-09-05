package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
