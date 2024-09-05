package com.aurionpro.main.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH ,CascadeType.DETACH})
	@JoinColumn(name="instructorId")
	@JsonIgnore
	private Instructor instructor;
	
	@ManyToMany(mappedBy = "courses",cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	private List<Student> students;
		
}

