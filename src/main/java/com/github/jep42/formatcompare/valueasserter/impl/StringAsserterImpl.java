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
	public void assertCondition(String masterValue, String slaveValue, String condition) throws AssertionException, FormatComparatorException {

		if (CONDITION_EQUAL.equals(condition)) {
			if (!masterValue.equals(slaveValue)) {
				throw new AssertionException(String.format(ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
			}
		} else if (CONDITION_UNEQUAL.equals(condition)) {
			if (masterValue.equals(slaveValue)) {
				throw new AssertionException(String.format(ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
			}
		} else if (CONDITION_CONTAINS.equals(condition)) {
			if (!masterValue.contains(slaveValue)) {
				throw new AssertionException(String.format(ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
			}
		} else if (CONDITION_CONTAINED.equals(condition)) {
			if (!slaveValue.contains(masterValue)) {
				throw new AssertionException(String.format(ASSERTION_FAILED_MESSAGE, masterValue, condition, slaveValue));
			}
		} else {
			throw new FormatComparatorException(String.format(COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE, "STRING", condition));
		}
	}

}
