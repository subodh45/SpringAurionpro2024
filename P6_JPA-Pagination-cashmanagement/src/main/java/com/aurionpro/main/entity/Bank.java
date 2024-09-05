package com.aurionpro.main.entity;

import java.util.List;

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

@Entity
@Table(name="banks")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Bank {

	@Id
	@Column(name="bankId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId;
	
	@Column(name="bankName")
	private String bankName;
	
	@Column(name="branch")
	private String branch;
	
	@Column(name="ifscCode")
	private String ifscCode;
	
	@OneToMany(mappedBy = "bank" , cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})	
	private List<SalaryAccount> accounts;
}
