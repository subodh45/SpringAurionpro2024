package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

}
