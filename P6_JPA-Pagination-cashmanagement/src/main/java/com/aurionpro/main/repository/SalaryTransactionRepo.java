package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.SalaryTransaction;

public interface SalaryTransactionRepo extends JpaRepository<SalaryTransaction, Integer>{

}
