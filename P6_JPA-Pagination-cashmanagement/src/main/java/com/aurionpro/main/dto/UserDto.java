package com.aurionpro.main.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserDto {

    private int userId;
	
	private String username;

	private String password;
}
