package com.github.jep42.formatcompare.util;

import java.util.TimeZone;

public class UserContext {

	private TimeZone timezone;

	private String dateTimeFormat;

	private String dateFormat;

	private String numberFormat;

	public UserContext(TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		super();
		this.timezone = timezone;
		this.dateTimeFormat = dateTimeFormat;
		this.dateFormat = dateFormat;
		this.numberFormat = numberFormat;
	}

	public TimeZone getTimezone() {
		return timezone;
	}

	public String getDateTimeFormat() {
		return dateTimeFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public String getNumberFormat() {
		return numberFormat;
	}


}
