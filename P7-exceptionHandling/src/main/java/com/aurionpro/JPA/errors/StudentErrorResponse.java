package com.aurionpro.JPA.errors;
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@AllArgsConstructor 
@Data 
public class StudentErrorResponse {
 
	 private int status; 
	 private String errorMessage; 
	 private long timestamp; 
	 	
}
