package com.aurionpro.Lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.Lending.entity.Payment;
import com.aurionpro.Lending.service.PaymentService;

@RestController
@RequestMapping("/lendingapp")
public class paymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getAllPayment()
	{
		return ResponseEntity.ok(paymentService.getAllPayment());
	}
	
	@GetMapping("/payments/{paymentId}")
	public ResponseEntity<Payment> findpayment(@PathVariable int paymentId)
	{
		return ResponseEntity.ok(paymentService.findPayment(paymentId));
	}
	
	@PostMapping("/payments")
	public String addPayment(@RequestBody Payment payment)
	{   
		paymentService.addPayment(payment);
		return "Payment Added Successfully";
	}
	
	@PutMapping("/payments")
	public String updatePayment(@RequestBody Payment payment)
	{
		paymentService.updatePayment(payment);
		return "payment updated successfully";
	}
}
