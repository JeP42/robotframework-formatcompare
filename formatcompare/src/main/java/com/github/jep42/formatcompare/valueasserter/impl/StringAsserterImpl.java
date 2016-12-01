package com.github.jep42.formatcompare.valueasserter.impl;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public class StringAsserterImpl implements ValueAsserter<String> {

	public static final String CONDITION_EQUAL = "=";

	public static final String CONDITION_UNEQUAL = "!=";

	public static final String CONDITION_CONTAINS = "&";

	public static final String CONDITION_CONTAINED = "$";


	@Override
	public void assertCondition(String masterValue, String slaveValue, String condition) throws AssertionException {
		if (CONDITION_EQUAL.equals(condition)) {
			checkEquals(masterValue, slaveValue, condition);
		} else if (CONDITION_UNEQUAL.equals(condition)) {
			checkUnequal(masterValue, slaveValue, condition);
		} else if (CONDITION_CONTAINS.equals(condition)) {
			checkContains(masterValue, slaveValue, condition);
		} else if (CONDITION_CONTAINED.equals(condition)) {
			checkContained(masterValue, slaveValue, condition);
		} else {
			throw new FormatComparatorException(String.format(ValueAsserterMessages.COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE, "STRING", condition));
		}
	}


	private void checkContained(String masterValue, String slaveValue, String condition) throws AssertionException {
		if (!slaveValue.contains(masterValue)) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	private void checkContains(String masterValue, String slaveValue, String condition) throws AssertionException {
		if (!masterValue.contains(slaveValue)) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	private void checkUnequal(String masterValue, String slaveValue, String condition) throws AssertionException {
		if (masterValue.equals(slaveValue)) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

	private void checkEquals(String masterValue, String slaveValue, String condition) throws AssertionException {
		if (!masterValue.equals(slaveValue)) {
			throw new AssertionException(String.format(ValueAsserterMessages.ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
		}
	}

}
