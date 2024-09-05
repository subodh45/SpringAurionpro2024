package com.aurionpro.main.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="students")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Data
public class Student {

	@Column(name="rollno")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rollno;
	
	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private int age ;
	
	@OneToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "addressId")
	private Address addressId;
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinTable(name="students-courses",
	        joinColumns = @JoinColumn(name="rollno"),
	        inverseJoinColumns = @JoinColumn(name="courseId")
			)
	private Set<Course>courses;
}
