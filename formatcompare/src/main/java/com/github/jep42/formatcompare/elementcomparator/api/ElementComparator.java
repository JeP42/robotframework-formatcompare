package com.github.jep42.formatcompare.elementcomparator.api;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.FormatComparatorException;

public interface ElementComparator {



	void initialize(FieldMapping mapping, FormatHandler master, FormatHandler slave);

	void compare();

}
