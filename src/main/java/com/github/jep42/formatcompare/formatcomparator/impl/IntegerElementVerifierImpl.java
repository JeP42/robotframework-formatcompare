package com.github.jep42.formatcompare.formatcomparator.impl;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formatcomparator.api.AbstractDataElementVerifier;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;
import com.github.jep42.formatcompare.util.DataVerifierException;
import com.github.jep42.formatcompare.valuecomparator.ValueComparatorFactory;
import com.github.jep42.formatcompare.valuecomparator.api.AssertionException;
import com.github.jep42.formatcompare.valuecomparator.api.ValueComparator;
import com.github.jep42.formatcompare.valueparser.ValueParserFactory;
import com.github.jep42.formatcompare.valueparser.api.ValueParser;

public class IntegerElementVerifierImpl extends AbstractDataElementVerifier {

	private Integer m;

	private Integer s;


	public IntegerElementVerifierImpl(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		super(mapping, master, slave);
	}

	@Override
	protected void getValues() throws DataVerifierException {
		try {
			this.m = masterFormatHandler.getIntegerValueWith(fieldMapping.getMasterSelector());
			this.s = slaveFormatHandler.getIntegerValueWith(fieldMapping.getSlaveSelector());
		} catch (FormatHandlerException e) {
			throw new DataVerifierException(String.format(GETVALUE_ERROR_MESSAGE, fieldMapping.toString(), e.getMessage()), e);
		}
	}

	@Override
	protected void parseValues() {
		ValueParser<Integer> valueParser = ValueParserFactory.getValueParserForInteger();
    	this.m = valueParser.parseValue(this.m, this.fieldMapping.getMasterOptions(), this.masterFormatHandler.getUserContext());
    	this.s = valueParser.parseValue(this.s, this.fieldMapping.getSlaveOptions(), this.slaveFormatHandler.getUserContext());
	}

	@Override
	protected void assertValues() throws DataVerifierException {
		ValueComparator<Integer> valueComparator = ValueComparatorFactory.getValueComparatorForInteger();
    	try {
    		valueComparator.assertCondition(this.m, this.s, this.fieldMapping.getCondition());
    	} catch (AssertionException e) {
			throw new DataVerifierException(String.format(ASSERTION_ERROR_MESSAGE, fieldMapping.toString(), e.getMessage()), e);
		}
	}
}
