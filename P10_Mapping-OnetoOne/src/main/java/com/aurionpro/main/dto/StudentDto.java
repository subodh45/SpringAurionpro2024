package com.aurionpro.main.dto;

import com.aurionpro.main.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class StudentDto {
    private int rollno;
	private String name;	
	private int age ;
}
