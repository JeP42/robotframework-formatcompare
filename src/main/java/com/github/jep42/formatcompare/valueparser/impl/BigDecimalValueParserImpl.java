package com.github.jep42.formatcompare.valueparser.impl;

import java.math.BigDecimal;
import java.util.List;

import com.github.jep42.formatcompare.util.UserContext;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class BigDecimalValueParserImpl implements ValueParser<BigDecimal> {

	@Override
	public BigDecimal parseValue(BigDecimal value, List<String> options, UserContext userContext) {
		return value;
	}


}
