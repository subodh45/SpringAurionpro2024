package com.aurionpro.main.exception;

public class SQLIntegrityConstraintViolationException extends RuntimeException {

	public SQLIntegrityConstraintViolationException(String message) {
        super(message);
    }
}
