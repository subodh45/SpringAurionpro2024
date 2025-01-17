package com.aurionpro.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Role;
import com.aurionpro.main.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);	
	Boolean existsByUsername(String username);
}
