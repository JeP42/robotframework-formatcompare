package com.github.jep42.formatcompare.valueasserter;

import java.math.BigDecimal;
import java.util.Date;

import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;
import com.github.jep42.formatcompare.valueasserter.impl.BigDecimalAsserterImpl;
import com.github.jep42.formatcompare.valueasserter.impl.DateAsserterImpl;
import com.github.jep42.formatcompare.valueasserter.impl.IntegerAsserterImpl;
import com.github.jep42.formatcompare.valueasserter.impl.StringAsserterImpl;

public final class ValueAsserterFactory {

	private ValueAsserterFactory() {
		super();
	}


	public static ValueAsserter<String> getValueComparatorForString() {
		return new StringAsserterImpl();
	}

	public static ValueAsserter<Date> getValueComparatorForDate() {
		return new DateAsserterImpl();
	}

	public static ValueAsserter<BigDecimal> getValueComparatorForDecimal() {
		return new BigDecimalAsserterImpl();
	}

	public static ValueAsserter<Integer> getValueComparatorForInteger() {
		return new IntegerAsserterImpl();
	}

}
