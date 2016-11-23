package com.github.jep42.formatcompare.valuecomparator.impl;

import org.junit.Test;

import com.github.jep42.formatcompare.util.DataVerifierException;
import com.github.jep42.formatcompare.valuecomparator.ValueComparatorFactory;
import com.github.jep42.formatcompare.valuecomparator.api.AssertionException;
import com.github.jep42.formatcompare.valuecomparator.api.ValueComparator;

public class StringValueComparatorImplTest {

	@Test(expected = DataVerifierException.class)
	public void assertCondition_conditionNotSupported() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peter", "pan", "<=");
	}

	@Test
	public void assertCondition_equal() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "peterpan", StringValueComparatorImpl.CONDITION_EQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_equalFail() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "tinkerbell", StringValueComparatorImpl.CONDITION_EQUAL);
	}

	@Test
	public void assertCondition_unequal() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "tinkerbell", StringValueComparatorImpl.CONDITION_UNEQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_unequalFail() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "peterpan", StringValueComparatorImpl.CONDITION_UNEQUAL);
	}

	@Test
	public void assertCondition_contains() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "pan", StringValueComparatorImpl.CONDITION_CONTAINS);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_containsFail() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "tinker", StringValueComparatorImpl.CONDITION_CONTAINS);
	}

	@Test
	public void assertCondition_contained() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peter", "peterpan", StringValueComparatorImpl.CONDITION_CONTAINED);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_containedFail() throws Exception {
		ValueComparator<String> comparator = ValueComparatorFactory.getValueComparatorForString();
		comparator.assertCondition("peterpan", "tinkerbell", StringValueComparatorImpl.CONDITION_CONTAINED);
	}

}
