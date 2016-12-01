package com.github.jep42.formatcompare.valueasserter.impl;

import java.math.BigDecimal;

import com.github.jep42.formatcompare.valueasserter.api.AssertionException;

public class BigDecimalAsserterImpl extends NumericAsserterImpl<BigDecimal> {

	@Override
	protected void smallerCheck(BigDecimal masterValue, BigDecimal slaveValue, String condition) throws AssertionException {
		if (masterValue.compareTo(slaveValue) <= 0) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	@Override
	protected void greaterCheck(BigDecimal masterValue, BigDecimal slaveValue, String condition) throws AssertionException {
		if (masterValue.compareTo(slaveValue) >= 0) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	@Override
	protected void unequalCheck(BigDecimal masterValue, BigDecimal slaveValue, String condition) throws AssertionException {
		if (masterValue.compareTo(slaveValue) == 0) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	@Override
	protected void equalCheck(BigDecimal masterValue, BigDecimal slaveValue, String condition) throws AssertionException {
		if (masterValue.compareTo(slaveValue) != 0) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}
}
