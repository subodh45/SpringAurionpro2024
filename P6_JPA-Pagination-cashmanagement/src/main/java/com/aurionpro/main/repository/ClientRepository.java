package com.aurionpro.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Page<Client> findBycompanyName(String name,Pageable page);
}
