package com.github.jep42.formatcompare.valuecomparator.impl;

import org.junit.Test;

import com.github.jep42.formatcompare.util.DataVerifierException;
import com.github.jep42.formatcompare.valuecomparator.ValueComparatorFactory;
import com.github.jep42.formatcompare.valuecomparator.api.AssertionException;
import com.github.jep42.formatcompare.valuecomparator.api.ValueComparator;

public class IntegerValueComparatorImplTest {

	@Test(expected = DataVerifierException.class)
	public void assertCondition_conditionNotSupported() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), "<=");
	}

	@Test
	public void assertCondition_equal() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), IntegerValueComparatorImpl.CONDITION_EQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_equalFail() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(43), IntegerValueComparatorImpl.CONDITION_EQUAL);
	}

	@Test
	public void assertCondition_unequal() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(43), IntegerValueComparatorImpl.CONDITION_UNEQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_unequalFail() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), IntegerValueComparatorImpl.CONDITION_UNEQUAL);
	}

	@Test
	public void assertCondition_greater() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(43), IntegerValueComparatorImpl.CONDITION_GREATER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_greaterFail() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), IntegerValueComparatorImpl.CONDITION_GREATER);
	}

	@Test
	public void assertCondition_smaller() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(41), IntegerValueComparatorImpl.CONDITION_SMALLER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_smallerFail() throws Exception {
		ValueComparator<Integer> comparator = ValueComparatorFactory.getValueComparatorForInteger();
		comparator.assertCondition(Integer.valueOf(42), Integer.valueOf(42), IntegerValueComparatorImpl.CONDITION_SMALLER);
	}

}
