package com.aurionpro.JPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.aurionpro.JPA.errors.StudentErrorResponse;

@ControllerAdvice
public class StudentExceptionHandler {

	
	 @ExceptionHandler 
	 public ResponseEntity<StudentErrorResponse> handleStudentDoesNotExistsException(StudentDoesNotExistsException exception) { 
	   
	  StudentErrorResponse error = new StudentErrorResponse(); 
	  error.setStatus(HttpStatus.NOT_FOUND.value()); 
	  error.setErrorMessage(exception.getMessage()); 
	  error.setTimestamp(System.currentTimeMillis()); 
	   
	  return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	 
	 @ExceptionHandler 
	 public ResponseEntity<StudentErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) { 
	   
	  StudentErrorResponse error = new StudentErrorResponse(); 
	  error.setStatus(HttpStatus.NOT_FOUND.value()); 
	  error.setErrorMessage("Enter integer only "); 
	  error.setTimestamp(System.currentTimeMillis()); 
	   
	  return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
