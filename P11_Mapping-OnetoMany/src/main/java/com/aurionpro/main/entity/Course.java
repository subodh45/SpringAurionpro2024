package com.aurionpro.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="courses")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Course {

	@Id
	@Column(name="couseId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="fees")
	private int fees;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="instructor")
	private Instructor instructor;
	
	
}
