package com.github.jep42.formatcompare.valueparser;

import java.math.BigDecimal;
import java.util.Date;

import com.github.jep42.formatcompare.valueparser.api.ValueParser;
import com.github.jep42.formatcompare.valueparser.impl.BigDecimalValueParserImpl;
import com.github.jep42.formatcompare.valueparser.impl.BooleanValueParserImpl;
import com.github.jep42.formatcompare.valueparser.impl.DateValueParserImpl;
import com.github.jep42.formatcompare.valueparser.impl.IntegerValueParserImpl;
import com.github.jep42.formatcompare.valueparser.impl.StringValueParserImpl;

public final class ValueParserFactory {

    private ValueParserFactory() {
        super();
    }

    public static ValueParser<String> getValueParserForString() {
        return new StringValueParserImpl();
    }

    public static ValueParser<Date> getValueParserForDate() {
        return new DateValueParserImpl();
    }

    public static ValueParser<BigDecimal> getValueParserForDecimal() {
        return new BigDecimalValueParserImpl();
    }

    public static ValueParser<Integer> getValueParserForInteger() {
        return new IntegerValueParserImpl();
    }

    public static ValueParser<Boolean> getValueParserForBoolean() {
        return new BooleanValueParserImpl();
    }


}
