package com.github.jep42.formatcompare.valueasserter.api;

public interface ValueAsserter<T> {



	void assertCondition(T masterValue, T slaveValue, String condition) throws AssertionException;

}
