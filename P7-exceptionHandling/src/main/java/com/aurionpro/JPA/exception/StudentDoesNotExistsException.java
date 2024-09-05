package com.aurionpro.JPA.exception;

public class StudentDoesNotExistsException extends RuntimeException {

	public String getMessage()
	{
		return "Student does not Exist.";
	}
}
