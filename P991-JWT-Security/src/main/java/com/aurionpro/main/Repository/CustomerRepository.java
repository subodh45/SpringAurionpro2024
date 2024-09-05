package com.aurionpro.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
