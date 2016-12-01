package com.github.jep42.formatcompare.elementcomparator.impl;

import java.util.Date;

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

public class DateElementComparatorImpl extends AbstractElementComparator {

	protected Date m;

	protected Date s;


	public DateElementComparatorImpl(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		super(mapping, master, slave);
	}

	@Override
	protected void getValues() {
		try {
			this.m = this.masterFormatHandler.getDateValueWith(this.fieldMapping.getMasterSelector());
			this.s = this.slaveFormatHandler.getDateValueWith(this.fieldMapping.getSlaveSelector());
		} catch (FormatHandlerException e) {
			throw new FormatComparatorException(String.format(ElementComparatorMessages.GETVALUE_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}

	@Override
	protected void parseValues() {
		ValueParser<Date> valueParser = ValueParserFactory.getValueParserForDate();

		this.m = valueParser.parseValue(this.m, this.fieldMapping.getMasterOptions(), this.masterFormatHandler.getUserContext());
		this.s = valueParser.parseValue(this.s, this.fieldMapping.getSlaveOptions(), this.slaveFormatHandler.getUserContext());
	}

	@Override
	protected void assertValues() {
		ValueAsserter<Date> valueComparator = ValueAsserterFactory.getValueComparatorForDate();
    	try {
    		valueComparator.assertCondition(this.m, this.s, this.fieldMapping.getCondition());
    	} catch (AssertionException e) {
			throw new FormatComparatorException(String.format(ElementComparatorMessages.ASSERTION_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}
}
