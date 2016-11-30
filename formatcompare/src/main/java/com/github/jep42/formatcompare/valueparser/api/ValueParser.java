package com.github.jep42.formatcompare.valueparser.api;

import java.util.List;

import com.github.jep42.formatcompare.util.UserContext;

public interface ValueParser<T> {

	static final String OPTION_SET_TIME_TO_END_OF_DAY = "SETTIMETOENDOFDAY";

	T parseValue(T value, List<String> options, UserContext userContext);



}
