package com.aurionpro.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
