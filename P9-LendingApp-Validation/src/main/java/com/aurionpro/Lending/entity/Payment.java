package com.aurionpro.Lending.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="payments")
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

	@Id
	@Column(name ="paymentId" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	
	
	@NotNull(message = "Payment date cannot be null")
	@PastOrPresent(message = "Payment date cannot be in the future")
	@Column(name = "payementDate")
	private LocalDate paymentDate;
	
	@NotNull(message = "Amount cannot be null")
	@Min(value = 1, message = "Amount must be at least 1")
	@Column(name="amount")
	private int amount;
	

	@NotNull(message = "Payment mode cannot be null")
	@Column(name = "paymentMode")
	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;
	
	@NotNull(message = "Payment status cannot be null")
	@Column(name = "paymentStatus")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	
}
