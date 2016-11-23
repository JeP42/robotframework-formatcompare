package com.github.jep42.formatcompare.formatcomparator.impl;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formatcomparator.api.AbstractDataElementVerifier;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.DataVerifierException;
import com.github.jep42.formatcompare.valuecomparator.ValueComparatorFactory;
import com.github.jep42.formatcompare.valuecomparator.api.AssertionException;
import com.github.jep42.formatcompare.valuecomparator.api.ValueComparator;
import com.github.jep42.formatcompare.valueparser.ValueParserFactory;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class StringElementVerifierImpl extends AbstractDataElementVerifier {

	private String m;

	private String s;


	public StringElementVerifierImpl(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		super(mapping, master, slave);
	}

	@Override
	protected void getValues() throws DataVerifierException {
		this.m = this.masterFormatHandler.getStringValueWith(this.fieldMapping.getMasterSelector());
    	this.s = this.slaveFormatHandler.getStringValueWith(this.fieldMapping.getSlaveSelector());
	}

	@Override
	protected void parseValues() throws DataVerifierException {
		ValueParser<String> valueParser = ValueParserFactory.getValueParserForString();
		this.m = valueParser.parseValue(this.m, this.fieldMapping.getMasterOptions(), this.masterFormatHandler.getUserContext());
		this.s = valueParser.parseValue(this.s, this.fieldMapping.getSlaveOptions(), this.slaveFormatHandler.getUserContext());
	}

	@Override
	protected void assertValues() throws DataVerifierException {
		ValueComparator<String> valueComparator = ValueComparatorFactory.getValueComparatorForString();
    	try {
			valueComparator.assertCondition(this.m, this.s, this.fieldMapping.getCondition());
		} catch (AssertionException e) {
			throw new DataVerifierException(String.format(ASSERTION_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}
}
