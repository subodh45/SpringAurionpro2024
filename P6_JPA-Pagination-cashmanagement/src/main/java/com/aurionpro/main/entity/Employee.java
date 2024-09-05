package com.aurionpro.main.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="employees")
@RequiredArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
  
	@Column(name="employeeId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="position")
	private String position;
	
	@Column(name="hireDate")
	private LocalDate hireDate;
	
	@Column(name="salary")
	private double salary;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="salaryAccount")
	private SalaryAccount salaryAccount;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="clientId")
	@JsonIgnore  //if you want client in employee then comment this 
    private Client client; 
	
}
