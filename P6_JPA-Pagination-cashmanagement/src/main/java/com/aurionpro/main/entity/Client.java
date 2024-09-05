package com.aurionpro.main.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="clients")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientId;
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="registrationNumber")
	private int registrationNumber;
	
	@Column(name="contactPerson")
	private String contactPerson;
	
	@Column(name="contactEmail")
	private String contactEmail;
	
	@Column(name="contactNumber")
	private long contactNumber;
	
	@Column(name="address")
	private String address;
	
	@Column(name="status")
	private String status;
	
	@Column(name="creationalDate")
	private LocalDate creationalDate;
	
	@Column(name ="KYCStatus")
	@Enumerated(EnumType.STRING)
	private KYCStatus kycStatus;
	
	@OneToMany(mappedBy = "client", cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	@JsonIgnore
	private List<Employee> employees;
}
