package com.aurionpro.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="admins")
@PrimaryKeyJoinColumn(name = "userId")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Admin extends User {

	@Column(unique = true)
	@NotBlank(message = "Admin Name cannot be blank")
    @Size(min = 2, max = 50, message = "Admin Name must be between 2 and 50 characters")
	@NotNull(message = "Admin Name cannot be Null.")
	private String adminName;
}




//package com.aurionpro.main.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Entity
//@Table(name="admins")
//@Data
//@AllArgsConstructor
//@RequiredArgsConstructor
//public class Admin {
//
//	@Id
//	@Column(name="adminId")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int adminId;
//	
//	@Column(name="adminName", unique = true)
//	@NotBlank(message = "Admin Name cannot be blank")
//    @Size(min = 2, max = 50, message = "Admin Name must be between 2 and 50 characters")
//	@NotNull(message = "Admin Name cannot be Null.")
//	private String adminName;
//	
//	@Column(name="password")
//	@NotBlank(message = "Password cannot be blank")
//    @Size(min = 6, message = "Password must be at least 6 characters long")
//	private String password;
//}
