package com.github.jep42.formatcompare.elementcomparator;

import com.github.jep42.formatcompare.elementcomparator.api.ElementComparator;
import com.github.jep42.formatcompare.elementcomparator.impl.DateElementComparatorImpl;
import com.github.jep42.formatcompare.elementcomparator.impl.DateTimeElementComparatorImpl;
import com.github.jep42.formatcompare.elementcomparator.impl.DecimalElementComparatorImpl;
import com.github.jep42.formatcompare.elementcomparator.impl.IntegerElementComparatorImpl;
import com.github.jep42.formatcompare.elementcomparator.impl.StringElementComparatorImpl;
import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.fieldmapper.api.FieldType;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.FormatComparatorException;

public class ElementComparatorFactory {

	public static ElementComparator getElementComparatorFor(FieldMapping mapping, FormatHandler master, FormatHandler slave) throws FormatComparatorException {
		if (FieldType.STRING.equals(mapping.getFieldType())) {
			return new StringElementComparatorImpl(mapping, master, slave);
		} else if (FieldType.DATETIME.equals(mapping.getFieldType())) {
			return new DateTimeElementComparatorImpl(mapping, master, slave);
		} else if (FieldType.DATE.equals(mapping.getFieldType())) {
			return new DateElementComparatorImpl(mapping, master, slave);
		} else if (FieldType.DECIMAL.equals(mapping.getFieldType())) {
			return new DecimalElementComparatorImpl(mapping, master, slave);
		} else if (FieldType.INTEGER.equals(mapping.getFieldType())) {
			return new IntegerElementComparatorImpl(mapping, master, slave);
		} else {
        	throw new FormatComparatorException("Mapping type '" + mapping.getFieldType().toString() + "' is not supported.");
        }
	}

}
