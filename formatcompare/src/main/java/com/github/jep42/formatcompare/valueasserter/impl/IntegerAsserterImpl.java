package com.github.jep42.formatcompare.valueasserter.impl;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public class IntegerAsserterImpl implements ValueAsserter<Integer> {

	public static final String CONDITION_EQUAL = "=";

	public static final String CONDITION_UNEQUAL = "!=";

	public static final String CONDITION_GREATER = ">";

	public static final String CONDITION_SMALLER = "<";

	@Override
	public void assertCondition(Integer masterValue, Integer slaveValue, String condition)
			throws AssertionException, FormatComparatorException {

		if (CONDITION_EQUAL.equals(condition)) {
			if (!(masterValue.compareTo(slaveValue) == 0)) {
				throw new AssertionException(String.format(ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
			}
		} else if (CONDITION_UNEQUAL.equals(condition)) {
			if (masterValue.compareTo(slaveValue) == 0) {
				throw new AssertionException(String.format(ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
			}
		} else if (CONDITION_GREATER.equals(condition)) {
			if (!(masterValue.compareTo(slaveValue) < 0)) {
				throw new AssertionException(String.format(ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
			}
		} else if (CONDITION_SMALLER.equals(condition)) {
			if (!(masterValue.compareTo(slaveValue) > 0)) {
				throw new AssertionException(String.format(ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
			}
		} else {
			throw new FormatComparatorException(String.format(COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE, "INTEGER", condition));
		}

	}

}
