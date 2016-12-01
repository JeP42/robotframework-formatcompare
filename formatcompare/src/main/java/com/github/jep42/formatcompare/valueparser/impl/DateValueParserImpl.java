package com.github.jep42.formatcompare.valueparser.impl;

import java.util.Date;
import java.util.List;

import com.github.jep42.formatcompare.util.DateHelper;
import com.github.jep42.formatcompare.util.UserContext;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class DateValueParserImpl implements ValueParser<Date> {

	private static final String OPTION_SET_TIME_TO_END_OF_DAY = "SETTIMETOENDOFDAY";

	@Override
	public Date parseValue(Date value, List<String> options, UserContext userContext) {

		if (options.contains(OPTION_SET_TIME_TO_END_OF_DAY)) {
			return DateHelper.setTimeToEndOfDay(value, userContext.getTimezone());
		}

		return value;
	}

}
