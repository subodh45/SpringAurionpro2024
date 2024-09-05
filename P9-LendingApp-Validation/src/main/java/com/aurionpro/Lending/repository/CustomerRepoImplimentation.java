package com.aurionpro.Lending.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.Lending.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepoImplimentation implements CustomerRepository {
  
	@Autowired
	private EntityManager manager;
	
	@Override
	public List<Customer> getAllCustomer() {
		
		TypedQuery<Customer> query = manager.createQuery("select c from Customer as c",Customer.class);
		
		return query.getResultList();
	}

	@Override
	public Customer findCustomer(int customerId) {
		
		return manager.find(Customer.class,customerId);
	}

	@Transactional
	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
	
		manager.persist(customer);
	}

	@Transactional
	@Override
	public void updatecustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		manager.merge(customer);
	}

}
