package com.aurionpro.main.exception;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {

	 private int status; 
	 private String errorMessage; 
	 private long timestamp; 
	 private Map<String, String> details; // To hold the field errors
	
}
