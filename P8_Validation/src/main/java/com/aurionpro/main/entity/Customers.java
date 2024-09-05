package com.aurionpro.main.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class Customers {

	@Id
	@Column(name ="customerId" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId ;
	
	@Column(name="firstName")
	@NotNull
	private String firstName;
	
	@Column(name="lastName")
	@NotNull
	private String lastName;
	
	@Column(name="date")
	private LocalDate eventDate;
	
	@Column(name="emailId")
	private String emailId;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
}
