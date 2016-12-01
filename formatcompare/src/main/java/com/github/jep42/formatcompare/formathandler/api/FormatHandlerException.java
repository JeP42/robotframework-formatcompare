package com.github.jep42.formatcompare.formathandler.api;

public class FormatHandlerException extends RuntimeException {

	private static final long serialVersionUID = 7059104732569410066L;

	public FormatHandlerException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	public FormatHandlerException(String message) {
		super(message);
	}

}
