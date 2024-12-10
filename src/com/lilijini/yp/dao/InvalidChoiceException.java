package com.lilijini.yp.dao;

public class InvalidChoiceException extends Exception {
	private String message;

	public InvalidChoiceException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
