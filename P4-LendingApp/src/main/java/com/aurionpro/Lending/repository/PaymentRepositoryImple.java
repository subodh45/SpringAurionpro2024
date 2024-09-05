package com.aurionpro.Lending.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.Lending.entity.Payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class PaymentRepositoryImple implements PaymentRepository {

	@Autowired
	private EntityManager manager ;
	
	@Override
	public List<Payment> getAllPayment() {
		
		TypedQuery<Payment> query = manager.createQuery("select p from Payment as p", Payment.class);
		return query.getResultList();
	}

	@Override
	public Payment findPayment(int paymentId) {
		
		return manager.find(Payment.class, paymentId);
	}

	@Transactional
	@Override
	public void addPayment(Payment payment) {
		
		manager.persist(payment);
	}

	@Transactional
	@Override
	public void updatePayment(Payment payment) {
		
	     manager.merge(payment);
	     }

}
