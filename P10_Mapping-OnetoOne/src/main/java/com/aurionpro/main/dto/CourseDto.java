package com.aurionpro.main.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CourseDto {

    private int courseId;
	private String name;
	private int duration;
	private int fees;
	
}
