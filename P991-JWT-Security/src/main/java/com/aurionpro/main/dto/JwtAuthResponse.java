package com.aurionpro.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class JwtAuthResponse {

	private String accessToken;
	private String tokenType="Bearer";
	
}
