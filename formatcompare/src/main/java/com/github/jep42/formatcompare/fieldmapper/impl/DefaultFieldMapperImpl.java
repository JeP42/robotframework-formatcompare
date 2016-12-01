package com.github.jep42.formatcompare.fieldmapper.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.jep42.easycsvmap.EasyCSVMap;
import com.github.jep42.formatcompare.fieldmapper.api.FieldMapper;
import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.fieldmapper.api.InvalidMapfileException;

/**
 * Handles CSV based map files
 * Format:
 *
 * masterSelector;slaveSelector;type;condition;masteroptions;slaveoptions
 *
 *
 */
public class DefaultFieldMapperImpl implements FieldMapper {

	private static final String UNEXPECTED_NUMBER_OF_COLUMNS_MESSAGE = "The actual number of columns (%s) does not match the number of expected config options (%s)";

	private static final int NUMBER_OF_CONFIG_OPTIONS = 6;

	List<FieldMapping> fieldMappings = new ArrayList<>();


	public DefaultFieldMapperImpl(String mapFilePath) throws InvalidMapfileException {
		EasyCSVMap csvmap = new EasyCSVMap();
		csvmap.parseCsvFromFile(mapFilePath);
		this.validateFormat(csvmap);
		this.initFieldMappings(csvmap);
	}

	/**
	 * Validates the format of the given mapping file. The format is considered to be valid if each of the following conditions are met:
	 * <li>the mapping file contains at least one field mapping (one line in the CSV file)</li>
	 * <li>each line contains 6 columns</li>
	 *
	 * @param csvmap
	 * @throws InvalidMapfileException
	 */
	private void validateFormat(EasyCSVMap csvmap) throws InvalidMapfileException {
		if (csvmap.getNumberOfCSVRows() == 0) {
			throw new InvalidMapfileException("The given mapping file is empty");
		}
		if (csvmap.getNumberOfCSVColumns() != NUMBER_OF_CONFIG_OPTIONS) {
			throw new InvalidMapfileException(String.format(UNEXPECTED_NUMBER_OF_COLUMNS_MESSAGE, csvmap.getNumberOfCSVColumns(), NUMBER_OF_CONFIG_OPTIONS));
		}
	}

	private void initFieldMappings(EasyCSVMap csvmap) {

		int numberOfMappings = csvmap.getNumberOfCSVRows();
		for (int i=0; i<numberOfMappings; i++) {
			String masterSelector = csvmap.getValues("{" + i + "}.0").get(i);
			String slaveSelector = csvmap.getValues("{" + i + "}.1").get(i);
			String type = csvmap.getValues("{" + i + "}.2").get(i);
			String condition = csvmap.getValues("{" + i + "}.3").get(i);
			String masteroptions = csvmap.getValues("{" + i + "}.4").get(i);
			String slaveoptions = csvmap.getValues("{" + i + "}.5").get(i);
			this.fieldMappings.add(FieldMappingFactory.createFieldMapping(masterSelector, slaveSelector, type, condition, masteroptions, slaveoptions, i));
		}
	}

	@Override
	public List<FieldMapping> getFieldMappings() {
		return this.fieldMappings;
	}

}
