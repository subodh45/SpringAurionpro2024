package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

}
