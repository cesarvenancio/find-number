package com.challenge.findnumber.exception;

public class FindNumberException extends Exception {

	public FindNumberException(String message) {
		super(message);
	}

	public FindNumberException(String message, Exception e) {
		super(message, e);
	}
}
