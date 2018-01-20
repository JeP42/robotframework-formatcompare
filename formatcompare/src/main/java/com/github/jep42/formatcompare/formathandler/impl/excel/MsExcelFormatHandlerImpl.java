package com.github.jep42.formatcompare.formathandler.impl.excel;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import com.github.jep42.formatcompare.formathandler.api.AbstractFormatHandler;
import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;
import com.github.jep42.formatcompare.util.BooleanHelper;
import com.github.jep42.formatcompare.util.DateHelper;

public class MsExcelFormatHandlerImpl extends AbstractFormatHandler {

    private MsExcelAccessor excelAccessor;


    public MsExcelFormatHandlerImpl(File excelFile, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
        super(timezone, dateTimeFormat, dateFormat, numberFormat);
        this.excelAccessor = MsExcelAccessor.getInstance(excelFile);
    }


    @Override
    public String getStringValueWith(String selector) {
        return this.excelAccessor.getStringValueVia(selector);
    }

    @Override
    public Date getDateValueWith(String selector) {
        String value = this.excelAccessor.getStringValueVia(selector);
        try {
            return DateHelper.getDateFromFormattedDateString(value, this.getUserContext().getDateFormat(), this.getUserContext().getTimezone());
        } catch (ParseException e) {
            throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getDateFormat(), "XLS(X)", e.getMessage()), e);
        }
    }

    @Override
    public Date getDateTimeValueWith(String selector) {
        String value = this.excelAccessor.getStringValueVia(selector);
        try {
            return DateHelper.getDateFromFormattedDateString(value, this.getUserContext().getDateTimeFormat(), this.getUserContext().getTimezone());
        } catch (ParseException e) {
            throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getDateFormat(), "XLS(X)", e.getMessage()), e);
        }
    }

    @Override
    public BigDecimal getDecimalValueWith(String selector) {
        String value = this.excelAccessor.getStringValueVia(selector);
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getNumberFormat(), "XLS(X)", e.getMessage()), e);
        }
    }

    @Override
    public Integer getIntegerValueWith(String selector) {
        String value = this.excelAccessor.getStringValueVia(selector);
        try {
            return new Integer(value);
        } catch (NumberFormatException e) {
            throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getNumberFormat(), "XLS(X)", e.getMessage()), e);
        }
    }

    @Override
    public Boolean getBooleanValueWith(String selector) {
    	 String booleanAsString = this.excelAccessor.getStringValueVia(selector);
         return BooleanHelper.getBooleanFromString(booleanAsString);
    }

}
