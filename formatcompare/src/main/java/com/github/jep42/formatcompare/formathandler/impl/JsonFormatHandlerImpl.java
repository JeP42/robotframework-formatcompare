package com.github.jep42.formatcompare.formathandler.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import com.github.jep42.formatcompare.formathandler.api.AbstractFormatHandler;
import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;
import com.github.jep42.formatcompare.util.BooleanHelper;
import com.github.jep42.formatcompare.util.DateHelper;
import com.github.jep42.formatcompare.util.NumericHelper;

public class JsonFormatHandlerImpl extends AbstractFormatHandler {

    private Object jsonObject;

    public JsonFormatHandlerImpl(String content, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
        super(timezone, dateTimeFormat, dateFormat, numberFormat);
        jsonObject = JSONAccessor.getJSONObject(content);
    }


    @Override
    public String getStringValueWith(String selector) {
        return JSONAccessor.getJSONValue(this.jsonObject, selector);
    }

    @Override
    public Date getDateValueWith(String selector) {
        String value = JSONAccessor.getJSONValue(this.jsonObject, selector);
        try {
            return DateHelper.getDateFromFormattedDateString(value, this.getUserContext().getDateFormat(), this.getUserContext().getTimezone());
        } catch (ParseException e) {
            throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getDateFormat(), "JSON", e.getMessage()), e);
        }
    }

    @Override
    public Date getDateTimeValueWith(String selector) {
        String value = JSONAccessor.getJSONValue(this.jsonObject, selector);
        try {
            return DateHelper.getDateFromFormattedDateString(value, this.getUserContext().getDateTimeFormat(), this.getUserContext().getTimezone());
        } catch (ParseException e) {
            throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getDateTimeFormat(), "JSON", e.getMessage()), e);
        }
    }

    @Override
    public BigDecimal getDecimalValueWith(String selector) {
        Object value = JSONAccessor.getJSONValue(this.jsonObject, selector);
        if (value instanceof Double) {
            return new BigDecimal((Double) value);
        } else {
            return createBigDecimalFromString(value.toString());
        }
    }

    private BigDecimal createBigDecimalFromString(String value) {
        try {
            return NumericHelper.getBigDecimalFromString(value, this.getUserContext().getNumberFormat());
        } catch (ParseException e) {
            throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, this.getUserContext().getNumberFormat(), "JSON", e.getMessage()), e);
        }
    }


    @Override
    public Integer getIntegerValueWith(String selector) {
        Object value = JSONAccessor.getJSONValue(this.jsonObject, selector);
        if (value instanceof Integer) {
            return (Integer) value;
        } else {
            return createIntegerFromString(value.toString());
        }
    }


    private Integer createIntegerFromString(String value) {
         try {
             return new Integer(value);
         } catch (NumberFormatException e) {
             throw new FormatHandlerException(String.format(PARSING_ERROR_MESSAGE, value, "<INTEGER>", "JSON", e.getMessage()), e);
         }
    }


    @Override
    public Boolean getBooleanValueWith(String selector) {
        Object value = JSONAccessor.getJSONValue(this.jsonObject, selector);
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            return  BooleanHelper.getBooleanFromString(value.toString());
        }
    }

}
