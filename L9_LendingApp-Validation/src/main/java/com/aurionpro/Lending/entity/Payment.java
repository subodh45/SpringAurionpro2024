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

@Table(name="payments")
@Entity
public class Payment {

	@Id
	@Column(name ="paymentId" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	
	@Column(name = "payementDate")
	private LocalDate paymentDate;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name = "paymentMode")
	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;
	
	@Column(name = "paymentStatus")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	public Payment()
	{
		
	}

	public Payment(int paymentId, LocalDate paymentDate, int amount, PaymentMode paymentMode,
			PaymentStatus paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
}
