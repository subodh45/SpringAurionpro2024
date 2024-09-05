package com.aurionpro.main.errors;
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@AllArgsConstructor 
@Data 
public class CustomerErrorResponse {
 
	 private int status; 
	 private String errorMessage; 
	 private long timestamp; 
	 
	
}
