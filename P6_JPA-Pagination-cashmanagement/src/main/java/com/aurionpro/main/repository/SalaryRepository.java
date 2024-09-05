package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Salary;

public interface SalaryRepository extends  JpaRepository<Salary, Integer> {

}
