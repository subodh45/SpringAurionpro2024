package com.aurionpro.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Client;
import com.aurionpro.main.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Page<Employee> findByfirstName(String name,Pageable page);
}
