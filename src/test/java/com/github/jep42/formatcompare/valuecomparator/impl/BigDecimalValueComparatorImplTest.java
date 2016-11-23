package com.github.jep42.formatcompare.valuecomparator.impl;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.jep42.formatcompare.util.DataVerifierException;
import com.github.jep42.formatcompare.valuecomparator.ValueComparatorFactory;
import com.github.jep42.formatcompare.valuecomparator.api.AssertionException;
import com.github.jep42.formatcompare.valuecomparator.api.ValueComparator;

public class BigDecimalValueComparatorImplTest {

	@Test(expected = DataVerifierException.class)
	public void assertCondition_conditionNotSupported() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), "<=");
	}

	@Test
	public void assertCondition_equal() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), BigDecimalValueComparatorImpl.CONDITION_EQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_equalFail() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(43), BigDecimalValueComparatorImpl.CONDITION_EQUAL);
	}

	@Test
	public void assertCondition_unequal() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(43), BigDecimalValueComparatorImpl.CONDITION_UNEQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_unequalFail() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), BigDecimalValueComparatorImpl.CONDITION_UNEQUAL);
	}

	@Test
	public void assertCondition_greater() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(43), BigDecimalValueComparatorImpl.CONDITION_GREATER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_greaterFail() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), BigDecimalValueComparatorImpl.CONDITION_GREATER);
	}

	@Test
	public void assertCondition_smaller() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(41), BigDecimalValueComparatorImpl.CONDITION_SMALLER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_smallerFail() throws Exception {
		ValueComparator<BigDecimal> comparator = ValueComparatorFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), BigDecimalValueComparatorImpl.CONDITION_SMALLER);
	}


}
