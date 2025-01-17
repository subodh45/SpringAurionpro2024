package com.aurionpro.main.entity;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="accounts")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Account {
	
//	@Id
//	@Column(name="accountId")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int accountId;
	
	@Id
	@Column(name="accountNumber", unique = true)
	@NotNull(message = "Account number cannot be null")
    @Positive(message = "Account number must be positive")
	private long accountNumber;
	
	@Column(name="balance")
	@NotNull(message = "Balance cannot be null")
	@DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than 0")
	private double balance;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="customerId")
	private Customer customer;

	
	@OneToMany(mappedBy ="account",cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	private List<Transaction> transactions;
}
