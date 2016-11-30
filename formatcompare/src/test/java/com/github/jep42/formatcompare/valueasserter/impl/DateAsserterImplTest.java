package com.github.jep42.formatcompare.valueasserter.impl;

import java.util.Date;

import org.junit.Test;

import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.ValueAsserterFactory;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;
import com.github.jep42.formatcompare.valueasserter.impl.DateAsserterImpl;

public class DateAsserterImplTest {

	private static final int ONE_MINUTE_IN_MILLIS = 60 * 1000;

	@Test(expected = FormatComparatorException.class)
	public void assertCondition_conditionNotSupported() throws Exception {
		ValueAsserter<Date> comparator = ValueAsserterFactory.getValueComparatorForDate();
		comparator.assertCondition(new Date(), new Date(), "<=");
	}

	@Test
	public void assertCondition_equal() throws Exception {
		Date now = new Date();
		ValueAsserter<Date> comparator = ValueAsserterFactory.getValueComparatorForDate();
		comparator.assertCondition(now, now, DateAsserterImpl.CONDITION_EQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_equalFail() throws Exception {
		Date now = new Date();
		Date after = new Date(now.getTime() + ONE_MINUTE_IN_MILLIS);
		ValueAsserter<Date> comparator = ValueAsserterFactory.getValueComparatorForDate();
		comparator.assertCondition(now, after, DateAsserterImpl.CONDITION_EQUAL);
	}

	@Test
	public void assertCondition_before() throws Exception {
		Date now = new Date();
		Date earlier = new Date(now.getTime() - ONE_MINUTE_IN_MILLIS);
		ValueAsserter<Date> comparator = ValueAsserterFactory.getValueComparatorForDate();
		comparator.assertCondition(earlier, now, DateAsserterImpl.CONDITION_BEFORE);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_beforeFail() throws Exception {
		Date now = new Date();
		ValueAsserter<Date> comparator = ValueAsserterFactory.getValueComparatorForDate();
		comparator.assertCondition(now, now, DateAsserterImpl.CONDITION_BEFORE);
	}

	@Test
	public void assertCondition_after() throws Exception {
		Date now = new Date();
		Date after = new Date(now.getTime() + ONE_MINUTE_IN_MILLIS);
		ValueAsserter<Date> comparator = ValueAsserterFactory.getValueComparatorForDate();
		comparator.assertCondition(after, now, DateAsserterImpl.CONDITION_AFTER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_afterFail() throws Exception {
		Date now = new Date();
		ValueAsserter<Date> comparator = ValueAsserterFactory.getValueComparatorForDate();
		comparator.assertCondition(now, now, DateAsserterImpl.CONDITION_AFTER);
	}

}
