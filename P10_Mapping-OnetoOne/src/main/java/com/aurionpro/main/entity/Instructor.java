package com.aurionpro.main.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Table(name="instructor")
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Instructor {

	@Id
	@Column(name="instructorId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="qualification")
	private String qualification;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy = "instructor", cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	@JsonIgnore
	private List<Course> courses;
}
