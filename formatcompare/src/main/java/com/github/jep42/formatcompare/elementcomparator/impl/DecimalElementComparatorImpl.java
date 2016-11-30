package com.github.jep42.formatcompare.elementcomparator.impl;

import java.math.BigDecimal;

import com.github.jep42.formatcompare.elementcomparator.api.AbstractElementComparator;
import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;
import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.ValueAsserterFactory;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;
import com.github.jep42.formatcompare.valueparser.ValueParserFactory;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class DecimalElementComparatorImpl extends AbstractElementComparator {

	private BigDecimal m;

	private BigDecimal s;


	public DecimalElementComparatorImpl(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		super(mapping, master, slave);
	}

	@Override
	protected void getValues() throws FormatComparatorException {
		try {
			this.m = this.masterFormatHandler.getDecimalValueWith(this.fieldMapping.getMasterSelector());
			this.s = this.slaveFormatHandler.getDecimalValueWith(this.fieldMapping.getSlaveSelector());
		} catch (FormatHandlerException e) {
			throw new FormatComparatorException(String.format(GETVALUE_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}

	@Override
	protected void parseValues() {
		ValueParser<BigDecimal> valueParser = ValueParserFactory.getValueParserForDecimal();
    	m = valueParser.parseValue(this.m, this.fieldMapping.getMasterOptions(), this.masterFormatHandler.getUserContext());
    	s = valueParser.parseValue(this.s, this.fieldMapping.getSlaveOptions(), this.slaveFormatHandler.getUserContext());
	}

	@Override
	protected void assertValues() throws FormatComparatorException {
		ValueAsserter<BigDecimal> valueComparator = ValueAsserterFactory.getValueComparatorForDecimal();
    	try {
    		valueComparator.assertCondition(this.m, this.s, this.fieldMapping.getCondition());
		} catch (AssertionException e) {
			throw new FormatComparatorException(String.format(ASSERTION_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}
}
