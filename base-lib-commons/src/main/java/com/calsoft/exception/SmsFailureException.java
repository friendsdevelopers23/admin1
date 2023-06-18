package com.calsoft.exception;

import org.springframework.http.HttpStatus;


public class SmsFailureException extends Exception {

	public SmsFailureException(String message) {
		super(message);

	}
}