package com.github.jep42.formatcompare.fieldmapper;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapper;
import com.github.jep42.formatcompare.fieldmapper.api.InvalidMapfileException;
import com.github.jep42.formatcompare.fieldmapper.impl.DefaultFieldMapperImpl;
import com.github.jep42.formatcompare.util.FormatComparatorException;

public final class FieldMapperFactory {

	private static final String INVALID_MAPFILE_FORMAT = "The format if the given mapfile is invalid. Message %s1";


	private FieldMapperFactory() {
		super();
	}


	public static FieldMapper getFieldMapper(String mapFilePath) {
		try {
			return new DefaultFieldMapperImpl(mapFilePath);
		} catch (InvalidMapfileException e) {
			throw new FormatComparatorException(String.format(INVALID_MAPFILE_FORMAT, e.getMessage()), e);
		}
	}

}
