package com.github.jep42.formatcompare.elementcomparator.api;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;

public abstract class AbstractElementComparator implements ElementComparator {

	protected FieldMapping fieldMapping;

	protected FormatHandler masterFormatHandler;

	protected FormatHandler slaveFormatHandler;


	public AbstractElementComparator(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		this.initialize(mapping, master, slave);
	}

	protected abstract void getValues();

	protected abstract void parseValues();

	protected abstract void assertValues();

	@Override
	public void initialize(FieldMapping mapping, FormatHandler master, FormatHandler slave) {
		this.fieldMapping = mapping;
		this.masterFormatHandler = master;
		this.slaveFormatHandler = slave;
	}

	@Override
	public void compare() {
		this.getValues();

		this.parseValues();

		this.assertValues();
	}


}
