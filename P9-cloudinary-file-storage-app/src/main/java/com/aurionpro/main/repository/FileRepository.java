package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> { 
}

