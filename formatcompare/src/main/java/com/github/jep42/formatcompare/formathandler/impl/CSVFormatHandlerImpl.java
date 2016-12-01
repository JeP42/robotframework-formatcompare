package com.github.jep42.formatcompare.formathandler.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import com.github.jep42.easycsvmap.EasyCSVMap;
import com.github.jep42.formatcompare.formathandler.api.AbstractFormatHandler;
import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;
import com.github.jep42.formatcompare.util.DateHelper;
import com.github.jep42.formatcompare.util.NumericHelper;

public class CSVFormatHandlerImpl extends AbstractFormatHandler {

	private EasyCSVMap csvObject;


	public CSVFormatHandlerImpl(String content, int headerLineIndex, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		super(timezone, dateTimeFormat, dateFormat, numberFormat);
		this.csvObject = CSVAccessor.getCsvObject(content, headerLineIndex);
	}

	@Override
	public String getStringValueWith(String selector) {
		return CSVAccessor.getFirstMatchingCsvValue(csvObject, selector);
	}

	@Override
	public Date getDateValueWith(String selector) {
		String value = CSVAccessor.getFirstMatchingCsvValue(csvObject, selector);
		try {
			return DateHelper.getDateFromFormattedDateString(value, this.getUserContext().getDateFormat(), this.getUserContext().getTimezone());
		} catch (ParseException e) {
			throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getDateFormat(), "CSV", e.getMessage()), e);
		}
	}

	@Override
	public Date getDateTimeValueWith(String selector) {
		String value = CSVAccessor.getFirstMatchingCsvValue(csvObject, selector);
		try {
			return DateHelper.getDateFromFormattedDateString(value, this.getUserContext().getDateTimeFormat(), this.getUserContext().getTimezone());
		} catch (ParseException e) {
			throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getDateTimeFormat(), "CSV", e.getMessage()), e);
		}
	}

	@Override
	public BigDecimal getDecimalValueWith(String selector) {
		String value = CSVAccessor.getFirstMatchingCsvValue(csvObject, selector);
		try {
			return NumericHelper.getBigDecimalFromString(value, this.getUserContext().getNumberFormat());
		} catch (ParseException e) {
			throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getNumberFormat(), "CSV", e.getMessage()), e);
		}
	}

	@Override
	public Integer getIntegerValueWith(String selector) {
		String value = CSVAccessor.getFirstMatchingCsvValue(csvObject, selector);
		try {
			return new Integer(value);
		} catch (NumberFormatException e) {
			throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, "<INTEGER>", "CSV", e.getMessage()), e);
		}
	}


}
