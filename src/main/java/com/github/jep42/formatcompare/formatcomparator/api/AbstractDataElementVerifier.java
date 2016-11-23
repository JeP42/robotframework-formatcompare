package com.github.jep42.formatcompare.formatcomparator.api;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.DataVerifierException;

public abstract class AbstractDataElementVerifier implements DataElementVerifier {

	protected FieldMapping fieldMapping;

	protected FormatHandler masterFormatHandler;

	protected FormatHandler slaveFormatHandler;


	public AbstractDataElementVerifier(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		this.initialize(mapping, master, slave);
	}

	protected abstract void getValues() throws DataVerifierException;

	protected abstract void parseValues() throws DataVerifierException;

	protected abstract void assertValues() throws DataVerifierException;

	@Override
	public void initialize(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		this.fieldMapping = mapping;
		this.masterFormatHandler = master;
		this.slaveFormatHandler = slave;
	}

	@Override
	public void verify() throws DataVerifierException {
		this.getValues();

		this.parseValues();

		this.assertValues();
	}


}
