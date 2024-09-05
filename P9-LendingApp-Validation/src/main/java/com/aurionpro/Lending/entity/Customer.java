package com.aurionpro.Lending.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

	@Id
	@Column(name ="customerId" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId ;
	
	@Column(name="firstName")
	@NotNull
	@Size(min = 2, max = 50 ,message = "name must be of  min 3 letters .")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabets")
	private String firstName;
	
	@Column(name="lastName")
	@NotNull
	@Size(min = 2, max = 50,message = "name must be of  min 3 letters .")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabets")
	private String lastName;
	
	@PastOrPresent(message = "future date is not allowed ")
	@NotNull
	@Column(name="date")
	private LocalDate eventDate;
	
	@Column(name="emialId")
	@NotNull
	@Email(message = "enter valid Email.")
	private String emailId;
	
	@Pattern(regexp="(^$|[0-9]{10})" ,message = "enter valid phone Number .")
	@Column(name="mobile_number",length=10,nullable=false,unique=true)
	private String mobileNumber;

	

	
		
}
