package com.github.jep42.robotformatcompare;

import java.io.IOException;

public class RobotFormatCompareException extends RuntimeException {

	private static final long serialVersionUID = -1445258229886950608L;

	public RobotFormatCompareException(String message) {
		super(message);
	}

	public RobotFormatCompareException(IOException e) {
		super(e);
	}


}
