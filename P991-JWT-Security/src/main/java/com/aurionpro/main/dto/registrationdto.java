package com.aurionpro.main.dto;

import com.aurionpro.main.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class registrationdto {

	private String username;
	private String password;
	private String role;
	
}
