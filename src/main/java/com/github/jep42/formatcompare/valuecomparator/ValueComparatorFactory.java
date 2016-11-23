package com.github.jep42.formatcompare.valuecomparator;

import java.math.BigDecimal;
import java.util.Date;

import com.github.jep42.formatcompare.valuecomparator.api.ValueComparator;
import com.github.jep42.formatcompare.valuecomparator.impl.BigDecimalValueComparatorImpl;
import com.github.jep42.formatcompare.valuecomparator.impl.DateValueComparatorImpl;
import com.github.jep42.formatcompare.valuecomparator.impl.IntegerValueComparatorImpl;
import com.github.jep42.formatcompare.valuecomparator.impl.StringValueComparatorImpl;

public class ValueComparatorFactory {


	public static ValueComparator<String> getValueComparatorForString() {
		return new StringValueComparatorImpl();
	}

	public static ValueComparator<Date> getValueComparatorForDate() {
		return new DateValueComparatorImpl();
	}

	public static ValueComparator<BigDecimal> getValueComparatorForDecimal() {
		return new BigDecimalValueComparatorImpl();
	}

	public static ValueComparator<Integer> getValueComparatorForInteger() {
		return new IntegerValueComparatorImpl();
	}

}
