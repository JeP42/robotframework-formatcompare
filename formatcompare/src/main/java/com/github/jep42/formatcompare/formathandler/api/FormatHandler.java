package com.github.jep42.formatcompare.formathandler.api;

import java.math.BigDecimal;
import java.util.Date;

import com.github.jep42.formatcompare.util.UserContext;

public interface FormatHandler {

	UserContext getUserContext();

	String getStringValueWith(String selector);

	Date getDateValueWith(String selector);

	Date getDateTimeValueWith(String masterSelector);

	BigDecimal getDecimalValueWith(String selector);

	Integer getIntegerValueWith(String selector);

}
