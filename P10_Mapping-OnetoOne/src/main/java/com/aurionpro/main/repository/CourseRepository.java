package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Course;

public interface CourseRepository  extends JpaRepository<Course, Integer>{

}
