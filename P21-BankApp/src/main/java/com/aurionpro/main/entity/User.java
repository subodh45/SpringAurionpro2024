package com.aurionpro.main.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank(message = "Username cannot be blank")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username;
	
	@NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_roles",
	        joinColumns = @JoinColumn(name="userId"),
	        inverseJoinColumns = @JoinColumn(name="roleId")
	)
	private List<Role> roles;
}




//package com.aurionpro.main.entity;
//
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//@Entity
//@Table(name="users")
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Data
//public class User {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int userId;
//	
//	@Column(name="username")
//	private String username;
//	
//	@Column(name="password")
//	private String password;
//	
//	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
//	@JoinTable(name="user-roles",
//	        joinColumns = @JoinColumn(name="userId"),
//	        inverseJoinColumns = @JoinColumn(name="roleId")
//			)
//	List<Role> roles;
//}
