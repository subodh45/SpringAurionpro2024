package com.aurionpro.Lending.service;

import java.util.List;

import com.aurionpro.Lending.entity.Payment;

public interface PaymentService {

	List<Payment> getAllPayment();	
	Payment findPayment (int paymentId);
	void addPayment(Payment payment);
	void updatePayment(Payment payment);
}
