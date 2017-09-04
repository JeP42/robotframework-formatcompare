package com.github.jep42.formatcompare.valueparser.impl;

import java.util.List;

import com.github.jep42.formatcompare.util.UserContext;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class BooleanValueParserImpl implements ValueParser<Boolean> {

    @Override
    public Boolean parseValue(Boolean value, List<String> options, UserContext userContext) {
        return value;
    }

}
