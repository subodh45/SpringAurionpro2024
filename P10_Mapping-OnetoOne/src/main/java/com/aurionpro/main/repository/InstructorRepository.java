package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Instructor;

public interface InstructorRepository  extends JpaRepository<Instructor, Integer>{

}
