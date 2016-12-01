package com.github.jep42.formatcompare.elementcomparator.impl;

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

public class IntegerElementComparatorImpl extends AbstractElementComparator {

	private Integer m;

	private Integer s;


	public IntegerElementComparatorImpl(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		super(mapping, master, slave);
	}

	@Override
	protected void getValues() throws FormatComparatorException {
		try {
			this.m = masterFormatHandler.getIntegerValueWith(fieldMapping.getMasterSelector());
			this.s = slaveFormatHandler.getIntegerValueWith(fieldMapping.getSlaveSelector());
		} catch (FormatHandlerException e) {
			throw new FormatComparatorException(String.format(ElementComparatorMessages.GETVALUE_ERROR_MESSAGE, fieldMapping.toString(), e.getMessage()), e);
		}
	}

	@Override
	protected void parseValues() {
		ValueParser<Integer> valueParser = ValueParserFactory.getValueParserForInteger();
    	this.m = valueParser.parseValue(this.m, this.fieldMapping.getMasterOptions(), this.masterFormatHandler.getUserContext());
    	this.s = valueParser.parseValue(this.s, this.fieldMapping.getSlaveOptions(), this.slaveFormatHandler.getUserContext());
	}

	@Override
	protected void assertValues() throws FormatComparatorException {
		ValueAsserter<Integer> valueComparator = ValueAsserterFactory.getValueComparatorForInteger();
    	try {
    		valueComparator.assertCondition(this.m, this.s, this.fieldMapping.getCondition());
    	} catch (AssertionException e) {
			throw new FormatComparatorException(String.format(ElementComparatorMessages.ASSERTION_ERROR_MESSAGE, fieldMapping.toString(), e.getMessage()), e);
		}
	}
}
