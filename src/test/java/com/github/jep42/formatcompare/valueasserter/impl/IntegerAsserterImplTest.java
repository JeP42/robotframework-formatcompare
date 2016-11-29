package com.github.jep42.formatcompare.valueasserter.impl;

import org.junit.Test;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.ValueAsserterFactory;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public class IntegerAsserterImplTest {

	@Test(expected = FormatComparatorException.class)
	public void assertCondition_conditionNotSupported() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), "<=");
	}

	@Test
	public void assertCondition_equal() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), IntegerAsserterImpl.CONDITION_EQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_equalFail() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(43), IntegerAsserterImpl.CONDITION_EQUAL);
	}

	@Test
	public void assertCondition_unequal() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(43), IntegerAsserterImpl.CONDITION_UNEQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_unequalFail() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), IntegerAsserterImpl.CONDITION_UNEQUAL);
	}

	@Test
	public void assertCondition_greater() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(43), IntegerAsserterImpl.CONDITION_GREATER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_greaterFail() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), IntegerAsserterImpl.CONDITION_GREATER);
	}

	@Test
	public void assertCondition_smaller() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(41), IntegerAsserterImpl.CONDITION_SMALLER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_smallerFail() throws Exception {
		ValueAsserter<Integer> comparator = ValueAsserterFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), IntegerAsserterImpl.CONDITION_SMALLER);
	}

}
