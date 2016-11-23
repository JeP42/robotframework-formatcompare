package com.github.jep42.formatcompare.fieldmapper.impl;

import java.util.Arrays;
import java.util.List;

import com.github.jep42.formatcompare.fieldmapper.api.FieldMapping;
import com.github.jep42.formatcompare.fieldmapper.api.FieldType;

public class DefaultFieldMappingImpl implements FieldMapping {


	private String masterSelector;

	private String slaveSelector;

	private FieldType type;

	private String condition;

	private List<String> masterOptions;

	private List<String> slaveOptions;

	private int mappingFileLineIndex;

	public DefaultFieldMappingImpl(String masterSelector, String slaveSelector, String type, String condition, String masterOptions, String slaveOptions, int mappingFileLineindex) {
		this.masterSelector = masterSelector;
		this.slaveSelector = slaveSelector;
		this.type = FieldType.valueOf(type);
		this.condition = condition;
		this.masterOptions = Arrays.asList(masterOptions.split(","));
		this.slaveOptions = Arrays.asList(slaveOptions.split(","));
		this.mappingFileLineIndex = mappingFileLineindex;
	}

	@Override
	public String getMasterSelector() {
		return this.masterSelector;
	}

	@Override
	public String getSlaveSelector() {
		return this.slaveSelector;
	}

	@Override
	public FieldType getFieldType() {
		return this.type;
	}

	@Override
	public String getCondition() {
		return this.condition;
	}

	@Override
	public List<String> getMasterOptions() {
		return this.masterOptions;
	}

	@Override
	public List<String> getSlaveOptions() {
		return this.slaveOptions;
	}

	@Override
	public int getMappingFileLineIndex() {
		return this.getMappingFileLineIndex();
	}

	@Override
	public String toString() {
		return "DefaultFieldMappingImpl [masterSelector=" + masterSelector + ", slaveSelector=" + slaveSelector
				+ ", type=" + type + ", condition=" + condition + ", masterOptions=" + masterOptions + ", slaveOptions="
				+ slaveOptions + ", mappingFileLineIndex=" + mappingFileLineIndex + "]";
	}
}
