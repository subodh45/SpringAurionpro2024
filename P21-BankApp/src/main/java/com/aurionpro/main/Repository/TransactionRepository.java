package com.aurionpro.main.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.dto.TransactionDto;
import com.aurionpro.main.entity.Account;
import com.aurionpro.main.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer >{

	Page<Transaction> findByAccount(Account account,Pageable pageable);
}
