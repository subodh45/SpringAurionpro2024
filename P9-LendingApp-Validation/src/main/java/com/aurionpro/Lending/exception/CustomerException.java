package com.aurionpro.Lending.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.Lending.errors.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomerException {

	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
	{
		 Map<String, String> errors = new HashMap<>();
	        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
	            errors.put(error.getField(), error.getDefaultMessage());
	        }
		
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Validation failed. ");
		error.setDetails(errors);  // Set the field-specific error messages
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception)
	{
//		 Map<String, String> errors = new HashMap<>();
//	        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
//	            errors.put(error.getField(), error.getDefaultMessage());
//	        }
//		
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Invalid input ");
		//error.setDetails(errors);  // Set the field-specific error messages
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exception)
	{
//		 Map<String, String> errors = new HashMap<>();
//	        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
//	            errors.put(error.getField(), error.getDefaultMessage());
//	        }
		
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Exception caught ");
		//error.setDetails(errors);  // Set the field-specific error messages
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
