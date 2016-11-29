package com.github.jep42.formatcompare.valueasserter.impl;

import org.junit.Test;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.ValueAsserterFactory;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public class StringAsserterImplTest {

	@Test(expected = FormatComparatorException.class)
	public void assertCondition_conditionNotSupported() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peter", "pan", "<=");
	}

	@Test
	public void assertCondition_equal() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "peterpan", StringAsserterImpl.CONDITION_EQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_equalFail() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "tinkerbell", StringAsserterImpl.CONDITION_EQUAL);
	}

	@Test
	public void assertCondition_unequal() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "tinkerbell", StringAsserterImpl.CONDITION_UNEQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_unequalFail() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "peterpan", StringAsserterImpl.CONDITION_UNEQUAL);
	}

	@Test
	public void assertCondition_contains() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "pan", StringAsserterImpl.CONDITION_CONTAINS);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_containsFail() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "tinker", StringAsserterImpl.CONDITION_CONTAINS);
	}

	@Test
	public void assertCondition_contained() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peter", "peterpan", StringAsserterImpl.CONDITION_CONTAINED);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_containedFail() throws Exception {
		ValueAsserter<String> comparator = ValueAsserterFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "tinkerbell", StringAsserterImpl.CONDITION_CONTAINED);
	}

}
