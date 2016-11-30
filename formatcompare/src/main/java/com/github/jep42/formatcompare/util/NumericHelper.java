package com.github.jep42.formatcompare.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class NumericHelper {

	public static BigDecimal getBigDecimalFromString(String numericValue, String numberFormat) throws ParseException {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(numberFormat.charAt(0));
        symbols.setDecimalSeparator(numberFormat.charAt(1));

		DecimalFormat formatter = new DecimalFormat();
        formatter.setDecimalFormatSymbols(symbols);
        formatter.setGroupingSize(3);
        formatter.setMinimumIntegerDigits(1);
        formatter.setMaximumIntegerDigits(Integer.MAX_VALUE);

		return new BigDecimal(formatter.parse(numericValue).toString());
	}

}
