package com.github.jep42.formatcompare.valueparser.impl;

import java.util.Date;
import java.util.List;

import com.github.jep42.formatcompare.util.DateHelper;
import com.github.jep42.formatcompare.util.UserContext;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class DateValueParserImpl implements ValueParser<Date> {



	@Override
	public Date parseValue(Date value, List<String> options, UserContext userContext) {

		if (options.contains(ValueParser.OPTION_SET_TIME_TO_END_OF_DAY)) {
			value = DateHelper.setTimeToEndOfDay(value, userContext.getTimezone());
		}


		return value;
	}

}
