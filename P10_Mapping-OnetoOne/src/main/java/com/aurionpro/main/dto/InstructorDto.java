package com.aurionpro.main.dto;

import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class InstructorDto {

    private int instructorId;
	private String name;
	private String qualification;
	private String email;
	
}
