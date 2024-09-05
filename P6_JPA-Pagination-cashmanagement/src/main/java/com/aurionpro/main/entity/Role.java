package com.aurionpro.main.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="roles")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="roleId")
	private int roleId;
	
	@Column(name="roleName")
	private String roleName;
	
	@ManyToMany(mappedBy = "roles", cascade ={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	private Set<User>users;
}
