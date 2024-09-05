package com.aurionpro.Lending.repository;

import java.util.List;

import com.aurionpro.Lending.entity.Payment;

public interface PaymentRepository {

	List<Payment> getAllPayment();	
	Payment findPayment (int paymentId);
	void addPayment(Payment payment);
	void updatePayment(Payment payment);
}
