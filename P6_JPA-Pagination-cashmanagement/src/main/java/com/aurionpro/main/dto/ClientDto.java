package com.aurionpro.main.dto;

import java.time.LocalDate;

import com.aurionpro.main.entity.KYCStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ClientDto {
	private int clientId;

	private String companyName;

	private int registrationNumber;

	private String contactPerson;

	private String contactEmail;

	private long contactNumber;
	
	private String address;

	private String status;
	
	private LocalDate creationalDate;

	private KYCStatus kycStatus;
}
