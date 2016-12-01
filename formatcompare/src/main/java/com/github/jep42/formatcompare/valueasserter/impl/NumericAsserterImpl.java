package com.github.jep42.formatcompare.valueasserter.impl;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public abstract class NumericAsserterImpl<T> implements ValueAsserter<T> {

	public static final String CONDITION_EQUAL = "=";

	public static final String CONDITION_UNEQUAL = "!=";

	public static final String CONDITION_GREATER = ">";

	public static final String CONDITION_SMALLER = "<";

	@Override
	public void assertCondition(T masterValue, T slaveValue, String condition) throws AssertionException {
		if (CONDITION_EQUAL.equals(condition)) {
			equalCheck(masterValue, slaveValue, condition);
		} else if (CONDITION_UNEQUAL.equals(condition)) {
			unequalCheck(masterValue, slaveValue, condition);
		} else if (CONDITION_GREATER.equals(condition)) {
			greaterCheck(masterValue, slaveValue, condition);
		} else if (CONDITION_SMALLER.equals(condition)) {
			smallerCheck(masterValue, slaveValue, condition);
		} else {
			throw new FormatComparatorException(String.format(ValueAsserterMessages.COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE, "DECIMAL", condition));
		}
	}

	protected abstract void smallerCheck(T masterValue, T slaveValue, String condition) throws AssertionException;

	protected abstract void greaterCheck(T masterValue, T slaveValue, String condition) throws AssertionException;

	protected abstract void unequalCheck(T masterValue, T slaveValue, String condition) throws AssertionException;

	protected abstract void equalCheck(T masterValue, T slaveValue, String condition) throws AssertionException;

}
