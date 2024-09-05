package com.aurionpro.main.EmployeeRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
