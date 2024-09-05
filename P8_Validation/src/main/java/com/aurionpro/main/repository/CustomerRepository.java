package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Customers;

public interface CustomerRepository  extends JpaRepository<Customers, Integer> {

}
