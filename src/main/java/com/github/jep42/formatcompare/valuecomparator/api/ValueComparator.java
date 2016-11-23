package com.github.jep42.formatcompare.valuecomparator.api;

import com.github.jep42.formatcompare.util.DataVerifierException;

public interface ValueComparator<T> {

	static final String ASSERTION_FAILED_MESSAGE = "Condition not true: %s %s %s";

	static final String COMPARATOR_NOT_SUPPORTED_ERROR_MESSAGE = "The value comparator for %s does not support the condition: %s";

	void assertCondition(T masterValue, T slaveValue, String condition) throws AssertionException, DataVerifierException;

}
