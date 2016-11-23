package com.github.jep42.formatcompare.formatcomparator.api;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.DataVerifierException;

public interface DataElementVerifier {

	static final String ASSERTION_ERROR_MESSAGE = "An exception occured while asserting field mapping %s. Message: %s";

	static final String GETVALUE_ERROR_MESSAGE = "An exception occured while getting values for field mapping %s. Message: %s";

	void initialize(FieldMapping mapping, FormatHandler master, FormatHandler slave);

	void verify() throws DataVerifierException;

}
