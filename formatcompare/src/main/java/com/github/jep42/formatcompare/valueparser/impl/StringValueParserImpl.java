package com.github.jep42.formatcompare.valueparser.impl;

import java.util.List;

import com.github.jep42.formatcompare.util.UserContext;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class StringValueParserImpl implements ValueParser<String> {

	@Override
	public String parseValue(String value, List<String> options, UserContext userContext) {
		return value;
	}

}
