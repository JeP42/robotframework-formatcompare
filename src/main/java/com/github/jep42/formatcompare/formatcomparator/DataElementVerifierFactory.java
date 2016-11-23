package com.github.jep42.formatcompare.formatcomparator;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.fieldmapper.api.FieldType;
import com.github.jep42.formatcompare.formatcomparator.api.DataElementVerifier;
import com.github.jep42.formatcompare.formatcomparator.impl.DateElementVerifierImpl;
import com.github.jep42.formatcompare.formatcomparator.impl.DateTimeElementVerifierImpl;
import com.github.jep42.formatcompare.formatcomparator.impl.DecimalElementVerifierImpl;
import com.github.jep42.formatcompare.formatcomparator.impl.IntegerElementVerifierImpl;
import com.github.jep42.formatcompare.formatcomparator.impl.StringElementVerifierImpl;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.DataVerifierException;

public class DataElementVerifierFactory {

	public static DataElementVerifier getDataElementVerifierFor(FieldMapping mapping, FormatHandler master, FormatHandler slave) throws DataVerifierException {
		if (FieldType.STRING.equals(mapping.getFieldType())) {
			return new StringElementVerifierImpl(mapping, master, slave);
		} else if (FieldType.DATETIME.equals(mapping.getFieldType())) {
			return new DateTimeElementVerifierImpl(mapping, master, slave);
		} else if (FieldType.DATE.equals(mapping.getFieldType())) {
			return new DateElementVerifierImpl(mapping, master, slave);
		} else if (FieldType.DECIMAL.equals(mapping.getFieldType())) {
			return new DecimalElementVerifierImpl(mapping, master, slave);
		} else if (FieldType.INTEGER.equals(mapping.getFieldType())) {
			return new IntegerElementVerifierImpl(mapping, master, slave);
		} else {
        	throw new DataVerifierException("Mapping type '" + mapping.getFieldType().toString() + "' is not supported.");
        }
	}

}
