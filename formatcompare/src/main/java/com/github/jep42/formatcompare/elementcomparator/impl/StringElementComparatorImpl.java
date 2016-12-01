package com.github.jep42.formatcompare.elementcomparator.impl;

import com.github.jep42.formatcompare.elementcomparator.api.AbstractElementComparator;
import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.FormatComparatorException;
import com.github.jep42.formatcompare.valueasserter.ValueAsserterFactory;
import com.github.jep42.formatcompare.valueasserter.api.AssertionException;
import com.github.jep42.formatcompare.valueasserter.api.ValueAsserter;
import com.github.jep42.formatcompare.valueparser.ValueParserFactory;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class StringElementComparatorImpl extends AbstractElementComparator {

	private String m;

	private String s;


	public StringElementComparatorImpl(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		super(mapping, master, slave);
	}

	@Override
	protected void getValues() {
		this.m = this.masterFormatHandler.getStringValueWith(this.fieldMapping.getMasterSelector());
		this.s = this.slaveFormatHandler.getStringValueWith(this.fieldMapping.getSlaveSelector());
	}

	@Override
	protected void parseValues() {
		ValueParser<String> valueParser = ValueParserFactory.getValueParserForString();
		this.m = valueParser.parseValue(this.m, this.fieldMapping.getMasterOptions(), this.masterFormatHandler.getUserContext());
		this.s = valueParser.parseValue(this.s, this.fieldMapping.getSlaveOptions(), this.slaveFormatHandler.getUserContext());
	}

	@Override
	protected void assertValues() {
		ValueAsserter<String> valueComparator = ValueAsserterFactory.getValueComparatorForString();
    	try {
			valueComparator.assertCondition(this.m, this.s, this.fieldMapping.getCondition());
		} catch (AssertionException e) {
			throw new FormatComparatorException(String.format(ElementComparatorMessages.ASSERTION_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}
}
