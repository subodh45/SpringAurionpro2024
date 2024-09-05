package com.aurionpro.dbconnection.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.aurionpro.dbconnection.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
public class EmployeeRepoImplementation implements EmployeeRepository {

	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> query = manager.createQuery("select e from Employee e", Employee.class);
			
		return query.getResultList();
	}

	@Override
	public Employee getEmployee(int empId) {
	
		return manager.find(Employee.class, empId);
	}

	@Transactional
	@Override
	public void addEmployee(Employee employee) {
		
		manager.persist(employee);	
	}

	@Transactional
	@Override
	public void updateEmployee(Employee employee) {
		manager.merge(employee);
		
	}
	
	

}
