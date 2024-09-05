package com.aurionpro.main.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="transactions")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transactionId")
	private int transactionId;
	
	@NotNull(message = "Sender account number cannot be null")
	@Column(name="senderAccountNumber")
	private long senderAccountNumber;
	
	@NotNull(message = "Receiver account number cannot be null")
	@Column(name = "receiverAccountNumber")
	private long receiverAccountNumber;
	
	@NotNull(message = "Transaction type cannot be null")
	@Enumerated(EnumType.STRING)
	@Column(name="transactionType")
	private TransactionType transactionType;
	
	@PastOrPresent(message = "Transaction date cannot be in the future")
	@Column(name = "date")
	private LocalDate date;
	
	@Min(value = 1, message = "Amount must be greater than or equal to one")
	@Column(name = "amount")
	private int amount;
	
	@ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name="accountNumber")
	private Account account;
	
	 
}
