package com.github.jep42.formatcompare.valueparser.api;

import java.util.List;

import com.github.jep42.formatcompare.util.UserContext;

public interface ValueParser<T> {

	T parseValue(T value, List<String> options, UserContext userContext);



}
