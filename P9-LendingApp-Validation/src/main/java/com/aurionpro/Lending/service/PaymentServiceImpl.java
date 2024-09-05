package com.aurionpro.Lending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aurionpro.Lending.entity.Payment;
import com.aurionpro.Lending.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;
	
	@Override
	public List<Payment> getAllPayment() {
		
		return paymentRepo.getAllPayment();
	}

	@Override
	public Payment findPayment(int paymentId) {
		
		return paymentRepo.findPayment(paymentId);
	}

	@Override
	public void addPayment(Payment payment) {
		
		paymentRepo.addPayment(payment);
		
	}

	@Override
	public void updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentRepo.updatePayment(payment);
	}

	
}
