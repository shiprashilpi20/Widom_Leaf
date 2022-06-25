package com.wisdomleaf.assignment.exception;

public class InvalidTimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTimeException(final String e) {
		super(e);
	}
}
