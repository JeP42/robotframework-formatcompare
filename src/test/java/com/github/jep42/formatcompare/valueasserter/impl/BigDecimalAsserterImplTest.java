package com.github.jep42.formatcompare.valueasserter.impl;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.ValueAsserterFactory;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;

public class BigDecimalAsserterImplTest {

	@Test(expected = FormatComparatorException.class)
	public void assertCondition_conditionNotSupported() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), "<=");
	}

	@Test
	public void assertCondition_equal() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), BigDecimalAsserterImpl.CONDITION_EQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_equalFail() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(43), BigDecimalAsserterImpl.CONDITION_EQUAL);
	}

	@Test
	public void assertCondition_unequal() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(43), BigDecimalAsserterImpl.CONDITION_UNEQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_unequalFail() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), BigDecimalAsserterImpl.CONDITION_UNEQUAL);
	}

	@Test
	public void assertCondition_greater() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(43), BigDecimalAsserterImpl.CONDITION_GREATER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_greaterFail() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), BigDecimalAsserterImpl.CONDITION_GREATER);
	}

	@Test
	public void assertCondition_smaller() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(41), BigDecimalAsserterImpl.CONDITION_SMALLER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_smallerFail() throws Exception {
		ValueAsserter<BigDecimal> comparator = ValueAsserterFactory.getValueComparatorForDecimal();
		comparator.assertCondition(BigDecimal.valueOf(42), BigDecimal.valueOf(42), BigDecimalAsserterImpl.CONDITION_SMALLER);
	}


}
