package com.github.jep42.formatcompare.fieldmapper.api;

import java.util.List;

public interface FieldMapping {


	String getMasterSelector();

	String getSlaveSelector();

	FieldType getFieldType();

	String getCondition();

	List<String> getMasterOptions();

	List<String> getSlaveOptions();

	String toString();

	/**
	 * Returns the line index where the FieldMapping appears in the map file. This info is included in error
	 * messages to support easy analysis of errors and root causes.
	 */
	int getMappingFileLineIndex();


}
