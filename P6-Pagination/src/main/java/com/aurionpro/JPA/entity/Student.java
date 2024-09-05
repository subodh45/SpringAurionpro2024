package com.aurionpro.JPA.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {

	@Column(name = "rollno")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollno;	
	@Column(name="name")
	private String name;
	@Column(name="age")
    private int age ;
	
	
	
}
