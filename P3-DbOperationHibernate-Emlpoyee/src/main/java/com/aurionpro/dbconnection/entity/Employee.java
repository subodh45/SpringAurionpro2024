package com.aurionpro.dbconnection.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	@Column(name = "empId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;	
	@Column(name="name")
	private String name;
	@Column(name="salary")
    private int salary ;
	
	public Employee()
	{
		
	}
	
	public Employee( String name, int salary) {
		
		this.name = name;
		this.salary = salary;
	}

	

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [rollno=" + empId + ", name=" + name + ", salary=" + salary + "]";
	}

	
}
