package com.github.jep42.formatcompare.formathandler.api;

import java.math.BigDecimal;
import java.util.Date;

import com.github.jep42.formatcompare.util.UserContext;

public interface FormatHandler {

	UserContext getUserContext();

	String getStringValueWith(String selector) throws FormatHandlerException;

	Date getDateValueWith(String selector)throws FormatHandlerException;

	Date getDateTimeValueWith(String masterSelector)throws FormatHandlerException;

	BigDecimal getDecimalValueWith(String selector)throws FormatHandlerException;

	Integer getIntegerValueWith(String selector)throws FormatHandlerException;

}
