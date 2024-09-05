package com.aurionpro.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.main.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}