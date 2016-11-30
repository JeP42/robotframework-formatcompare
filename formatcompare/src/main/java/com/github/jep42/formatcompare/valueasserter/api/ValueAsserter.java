package com.github.jep42.formatcompare.valueasserter.api;

import com.github.jep42.formatcompare.util.FormatComparatorException;

public interface ValueAsserter<T> {

	static final String ASSERTION_FAILED_MESSAGE = "Condition not true: %s %s %s";

	static final String COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE = "The value comparator for %s does not support the condition: %s";

	void assertCondition(T masterValue, T slaveValue, String condition) throws AssertionException, FormatComparatorException;

}
