package com.github.jep42.formatcompare.formathandler.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import org.w3c.dom.Document;

import com.github.jep42.formatcompare.formathandler.api.AbstractFormatHandler;
import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;
import com.github.jep42.formatcompare.util.DateHelper;
import com.github.jep42.formatcompare.util.NumericHelper;

public class XMLFormatHandlerImpl extends AbstractFormatHandler {


	private Document xmlDocument;

	public XMLFormatHandlerImpl(String content, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat)  {
		super(timezone, dateTimeFormat, dateFormat, numberFormat);
		this.xmlDocument = XMLAccessor.getDOMFromXML(content);
	}

	@Override
	public String getStringValueWith(String selector) throws FormatHandlerException {
		return XMLAccessor.getXMLValue(xmlDocument, selector);
	}

	@Override
	public Date getDateValueWith(String selector) throws FormatHandlerException {
		String value = XMLAccessor.getXMLValue(xmlDocument, selector);
		try {
			return DateHelper.getDateFromFormattedDateString(value, this.getUserContext().getDateFormat(), this.getUserContext().getTimezone());
		} catch (ParseException e) {
			throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getDateFormat(), "XML", e.getMessage()), e);
		}
	}

	@Override
	public Date getDateTimeValueWith(String selector) throws FormatHandlerException {
		String value = XMLAccessor.getXMLValue(xmlDocument, selector);
		try {
			return DateHelper.getDateFromFormattedDateString(value, this.getUserContext().getDateTimeFormat(), this.getUserContext().getTimezone());
		} catch (ParseException e) {
			throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getDateTimeFormat(), "XML", e.getMessage()), e);
		}
	}

	@Override
	public BigDecimal getDecimalValueWith(String selector) throws FormatHandlerException {
		String value = XMLAccessor.getXMLValue(xmlDocument, selector);
		try {
			return NumericHelper.getBigDecimalFromString(value, this.getUserContext().getNumberFormat());
		} catch (ParseException e) {
			throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getNumberFormat(), "XML", e.getMessage()), e);
		}
	}

	@Override
	public Integer getIntegerValueWith(String selector) throws FormatHandlerException {
		String value = XMLAccessor.getXMLValue(xmlDocument, selector);
		try {
			return new Integer(value);
		} catch (NumberFormatException e) {
			throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, "<INTEGER>", "XML", e.getMessage()), e);
		}
	}



}
