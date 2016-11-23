package com.github.jep42.formatcompare.valuecomparator.impl;

import java.util.Date;

import org.junit.Test;

import com.github.jep42.formatcompare.util.DataVerifierException;
import com.github.jep42.formatcompare.valuecomparator.ValueComparatorFactory;
import com.github.jep42.formatcompare.valuecomparator.api.AssertionException;
import com.github.jep42.formatcompare.valuecomparator.api.ValueComparator;

public class DateValueComparatorImplTest {

	private static final int ONE_MINUTE_IN_MILLIS = 60 * 1000;

	@Test(expected = DataVerifierException.class)
	public void assertCondition_conditionNotSupported() throws Exception {
		ValueComparator<Date> comparator = ValueComparatorFactory.getValueComparatorForDate();
		comparator.assertCondition(new Date(), new Date(), "<=");
	}

	@Test
	public void assertCondition_equal() throws Exception {
		Date now = new Date();
		ValueComparator<Date> comparator = ValueComparatorFactory.getValueComparatorForDate();
		comparator.assertCondition(now, now, DateValueComparatorImpl.CONDITION_EQUAL);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_equalFail() throws Exception {
		Date now = new Date();
		Date after = new Date(now.getTime() + ONE_MINUTE_IN_MILLIS);
		ValueComparator<Date> comparator = ValueComparatorFactory.getValueComparatorForDate();
		comparator.assertCondition(now, after, DateValueComparatorImpl.CONDITION_EQUAL);
	}

	@Test
	public void assertCondition_before() throws Exception {
		Date now = new Date();
		Date earlier = new Date(now.getTime() - ONE_MINUTE_IN_MILLIS);
		ValueComparator<Date> comparator = ValueComparatorFactory.getValueComparatorForDate();
		comparator.assertCondition(earlier, now, DateValueComparatorImpl.CONDITION_BEFORE);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_beforeFail() throws Exception {
		Date now = new Date();
		ValueComparator<Date> comparator = ValueComparatorFactory.getValueComparatorForDate();
		comparator.assertCondition(now, now, DateValueComparatorImpl.CONDITION_BEFORE);
	}

	@Test
	public void assertCondition_after() throws Exception {
		Date now = new Date();
		Date after = new Date(now.getTime() + ONE_MINUTE_IN_MILLIS);
		ValueComparator<Date> comparator = ValueComparatorFactory.getValueComparatorForDate();
		comparator.assertCondition(after, now, DateValueComparatorImpl.CONDITION_AFTER);
	}

	@Test(expected = AssertionException.class)
	public void assertCondition_afterFail() throws Exception {
		Date now = new Date();
		ValueComparator<Date> comparator = ValueComparatorFactory.getValueComparatorForDate();
		comparator.assertCondition(now, now, DateValueComparatorImpl.CONDITION_AFTER);
	}

}
