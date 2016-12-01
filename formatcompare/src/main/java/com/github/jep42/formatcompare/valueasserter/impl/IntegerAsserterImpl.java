package com.github.jep42.formatcompare.valueasserter.impl;

import com.github.jep42.formatcompare.valueasserter.api.AssertionException;

public class IntegerAsserterImpl extends NumericAsserterImpl<Integer> {


	protected void smallerCheck(Integer masterValue, Integer slaveValue, String condition) throws AssertionException {
		if (masterValue.compareTo(slaveValue) <= 0) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	protected void greaterCheck(Integer masterValue, Integer slaveValue, String condition) throws AssertionException {
		if (masterValue.compareTo(slaveValue) >= 0) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	protected void unequalCheck(Integer masterValue, Integer slaveValue, String condition) throws AssertionException {
		if (masterValue.compareTo(slaveValue) == 0) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	protected void equalCheck(Integer masterValue, Integer slaveValue, String condition) throws AssertionException {
		if (masterValue.compareTo(slaveValue) != 0) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

}
