package com.aurionpro.main.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.main.errors.CustomerErrorResponse;



@ControllerAdvice
public class CustomerExceptionHandler {

	 @ExceptionHandler 
	 public ResponseEntity<CustomerErrorResponse>handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) { 
	   
	  CustomerErrorResponse error = new CustomerErrorResponse(); 
	  error.setStatus(HttpStatus.NOT_FOUND.value()); 
	  error.setErrorMessage("enter all value"); 
	  error.setTimestamp(System.currentTimeMillis()); 
	   
	  return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
