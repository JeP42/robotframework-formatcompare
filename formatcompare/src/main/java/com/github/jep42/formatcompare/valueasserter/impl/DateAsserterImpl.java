package com.github.jep42.formatcompare.valueasserter.impl;

import java.util.Date;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public class DateAsserterImpl implements ValueAsserter<Date> {

	public static final String CONDITION_EQUAL = "=";

	public static final String CONDITION_AFTER = ">";

	public static final String CONDITION_BEFORE = "<";


	@Override
	public void assertCondition(Date masterValue, Date slaveValue, String condition) throws AssertionException {
		if (CONDITION_EQUAL.equals(condition)) {
			checkEqual(masterValue, slaveValue, condition);
		} else if (CONDITION_AFTER.equals(condition)) {
			checkAfter(masterValue, slaveValue, condition);
		} else if (CONDITION_BEFORE.equals(condition)) {
			checkBefore(masterValue, slaveValue, condition);
		} else {
			throw new FormatComparatorException(String.format(ValueAsserterMessages.COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE, "DATE/TIME", condition));
		}
	}


	private void checkBefore(Date masterValue, Date slaveValue, String condition) throws AssertionException {
		if (!masterValue.before(slaveValue)) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	private void checkAfter(Date masterValue, Date slaveValue, String condition) throws AssertionException {
		if (!masterValue.after(slaveValue)) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	private void checkEqual(Date masterValue, Date slaveValue, String condition) throws AssertionException {
		if (!masterValue.equals(slaveValue)) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}
}
