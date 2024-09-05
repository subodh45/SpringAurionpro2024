package com.aurionpro.main.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="customers")
@PrimaryKeyJoinColumn(name = "userId")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Customer extends User {

	@NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabetic characters") 
	private String firstName;
	
	@NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only alphabetic characters")
    private String lastName;
	
	@Column(unique = true)
	@NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
	private String email;
	
	@OneToMany(mappedBy ="customer", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private List<Account> accounts;
}






















//package com.aurionpro.main.entity;
//
//import java.util.List;
//
//import org.hibernate.validator.constraints.UniqueElements;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Entity
//@Table(name="customers")
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Data
//public class Customer {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="customerId")
//	private int customerId;
//	
//	@Column(name="firstName")
//	@NotNull(message = "First name cannot be null")
//    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
//    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabetic characters") 
//	private String firstName;
//	
//	@Column(name="lastName")
//	@NotNull(message = "Last name cannot be null")
//    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
//    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only alphabetic characters")
//    private String lastName;
//	
//	@Column(name="email", unique = true)
//	@NotNull(message = "Email cannot be null")
//    @Email(message = "Email should be valid")
//	private String email;
//	
//	@Column(name="password")
//	@NotNull(message = "Password cannot be null")
//    @Size(min = 8, message = "Password must be at least 8 characters long")
//  	private String password;
//	
//	@OneToMany(mappedBy ="customer",cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
//	private List<Account> accounts;
//}
