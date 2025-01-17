package com.aurionpro.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Role;
import com.aurionpro.main.entity.User;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRolename(String rolename);
	
}
