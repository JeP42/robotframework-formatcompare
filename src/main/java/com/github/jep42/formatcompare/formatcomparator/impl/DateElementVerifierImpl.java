package com.github.jep42.formatcompare.formatcomparator.impl;

import java.util.Date;

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

public class DateElementVerifierImpl extends AbstractDataElementVerifier {

	protected Date m;

	protected Date s;


	public DateElementVerifierImpl(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		super(mapping, master, slave);
	}

	@Override
	protected void getValues() throws DataVerifierException {
		try {
			this.m = this.masterFormatHandler.getDateValueWith(this.fieldMapping.getMasterSelector());
			this.s = this.slaveFormatHandler.getDateValueWith(this.fieldMapping.getSlaveSelector());
		} catch (FormatHandlerException e) {
			throw new DataVerifierException(String.format(GETVALUE_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}

	@Override
	protected void parseValues() throws DataVerifierException{
		ValueParser<Date> valueParser = ValueParserFactory.getValueParserForDate();

		this.m = valueParser.parseValue(this.m, this.fieldMapping.getMasterOptions(), this.masterFormatHandler.getUserContext());
		this.s = valueParser.parseValue(this.s, this.fieldMapping.getSlaveOptions(), this.slaveFormatHandler.getUserContext());
	}

	@Override
	protected void assertValues() throws DataVerifierException {
		ValueComparator<Date> valueComparator = ValueComparatorFactory.getValueComparatorForDate();
    	try {
    		valueComparator.assertCondition(this.m, this.s, this.fieldMapping.getCondition());
    	} catch (AssertionException e) {
			throw new DataVerifierException(String.format(ASSERTION_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}
}
