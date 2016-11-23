package com.github.jep42.formatcompare.fieldmapper;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapper;
import com.github.jep42.formatcompare.fieldmapper.api.InvalidMapfileException;
import com.github.jep42.formatcompare.fieldmapper.impl.DefaultFieldMapperImpl;
import com.github.jep42.formatcompare.util.DataVerifierException;

public class FieldMapperFactory {

	private static final String INVALID_MAPFILE_FORMAT = "The format if the given mapfile is invalid. Message %s1";

	public static FieldMapper getFieldMapper(String mapFilePath) throws DataVerifierException {
		try {
			return new DefaultFieldMapperImpl(mapFilePath);
		} catch (InvalidMapfileException e) {
			throw new DataVerifierException(String.format(INVALID_MAPFILE_FORMAT, e.getMessage()), e);
		}
	}

}
