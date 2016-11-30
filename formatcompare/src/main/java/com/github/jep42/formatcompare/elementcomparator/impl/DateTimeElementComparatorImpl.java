package com.github.jep42.formatcompare.elementcomparator.impl;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.formathandler.api.FormatHandlerException;
import com.github.jep42.formatcompare.util.FormatComparatorException;

public class DateTimeElementComparatorImpl extends DateElementComparatorImpl {

	public DateTimeElementComparatorImpl(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		super(mapping, master, slave);
	}

	@Override
	protected void getValues() throws FormatComparatorException {
		try {
			this.m = this.masterFormatHandler.getDateTimeValueWith(this.fieldMapping.getMasterSelector());
			this.s = this.slaveFormatHandler.getDateTimeValueWith(this.fieldMapping.getSlaveSelector());
		} catch (FormatHandlerException e) {
			throw new FormatComparatorException(String.format(GETVALUE_ERROR_MESSAGE, this.fieldMapping.toString(), e.getMessage()), e);
		}
	}

}
