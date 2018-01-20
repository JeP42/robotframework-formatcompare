package com.github.jep42.robotformatcompare;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RobotFormatCompareTest {


	@Test
	public void initializeJsonFormatHandler() {
		this.initializeFormatHandler(RobotFormatCompare.CONFIG_JSON);
	}

	@Test
	public void initializeCsvFormatHandler() {
		this.initializeFormatHandler(RobotFormatCompare.CONFIG_CSV);
	}

	@Test
	public void initializeXmlFormatHandler() {
		this.initializeFormatHandler(RobotFormatCompare.CONFIG_XML);
	}
	
	@Test
	public void initializeExcelFormatHandler() {
		this.initializeFormatHandler(RobotFormatCompare.CONFIG_EXCEL);
	}

	private void initializeFormatHandler(String formatId) {
		String timezone = "GMT+01:00";
		String dateTimeFormat = "dtf";
		String dateFormat = "tf";
		String numberFormat = "nf";
		String numberFormatWithBraces = "\"nf\"";

		RobotFormatCompare r = new RobotFormatCompare();
		if (RobotFormatCompare.CONFIG_CSV.contentEquals(formatId)) {
			r.initializeCsvFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		} else if (RobotFormatCompare.CONFIG_JSON.contentEquals(formatId)) {
			r.initializeJsonFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		} else if (RobotFormatCompare.CONFIG_XML.contentEquals(formatId)) {
			r.initializeXmlFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		} else if (RobotFormatCompare.CONFIG_EXCEL.contentEquals(formatId)) {
			r.initializeExcelFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		}

		assertEquals(timezone, r.verfierConfigs.get(formatId).get(RobotFormatCompare.TIMEZONE));
		assertEquals(dateTimeFormat, r.verfierConfigs.get(formatId).get(RobotFormatCompare.DATETIMEFORMAT));
		assertEquals(dateFormat, r.verfierConfigs.get(formatId).get(RobotFormatCompare.DATEFORMAT));
		//number format is saved without enclosing braces
		assertEquals(numberFormat, r.verfierConfigs.get(formatId).get(RobotFormatCompare.NUMBERFORMAT));
	}
	
	
	@Test
	public void compareJsonWithXML_missingJsonConfig() {
		compareJsonWithXML_missingConfig(RobotFormatCompare.CONFIG_JSON);
	}

	@Test
	public void compareJsonWithXML_missingXmlConfig() {
		compareJsonWithXML_missingConfig(RobotFormatCompare.CONFIG_XML);
	}

	private void compareJsonWithXML_missingConfig(String missingFormatId) {
		String timezone = "GMT+01:00";
		String dateTimeFormat = "dtf";
		String dateFormat = "tf";
		String numberFormatWithBraces = "\"nf\"";

		RobotFormatCompare r = new RobotFormatCompare();

		if (RobotFormatCompare.CONFIG_JSON.contentEquals(missingFormatId)) {
			r.initializeXmlFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		} else if (RobotFormatCompare.CONFIG_XML.contentEquals(missingFormatId)) {
			r.initializeJsonFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		}

		try {
			r.compareJsonWithXML("/path/to/map.file", "json", "xml");
		} catch (RobotFormatCompareException e) {
			assertEquals("The format " + missingFormatId + " is not yet initialized.", e.getMessage());
		}
	}


	@Test
	public void compareCsvWithXML_missingCsvConfig() {
		compareCsvWithXML_missingConfig(RobotFormatCompare.CONFIG_CSV);
	}

	@Test
	public void compareCsvWithXML_missingXmlConfig() {
		compareCsvWithXML_missingConfig(RobotFormatCompare.CONFIG_XML);
	}


	private void compareCsvWithXML_missingConfig(String missingFormatId) {
		String timezone = "GMT+01:00";
		String dateTimeFormat = "dtf";
		String dateFormat = "tf";
		String numberFormatWithBraces = "\"nf\"";

		RobotFormatCompare r = new RobotFormatCompare();

		if (RobotFormatCompare.CONFIG_CSV.contentEquals(missingFormatId)) {
			r.initializeXmlFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		} else if (RobotFormatCompare.CONFIG_XML.contentEquals(missingFormatId)) {
			r.initializeCsvFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		}

		try {
			r.compareCsvWithXML("/path/to/map.file", "json", "xml", 0);
		} catch (RobotFormatCompareException e) {
			assertEquals("The format " + missingFormatId + " is not yet initialized.", e.getMessage());
		}
	}



	@Test
	public void compareCsvWithJSON_missingCsvConfig() {
		compareCsvWithJSON_missingConfig(RobotFormatCompare.CONFIG_CSV);
	}

	@Test
	public void compareCsvWithJSON_missingJSONConfig() {
		compareCsvWithJSON_missingConfig(RobotFormatCompare.CONFIG_JSON);
	}


	private void compareCsvWithJSON_missingConfig(String missingFormatId) {
		String timezone = "GMT+01:00";
		String dateTimeFormat = "dtf";
		String dateFormat = "tf";
		String numberFormatWithBraces = "\"nf\"";

		RobotFormatCompare r = new RobotFormatCompare();

		if (RobotFormatCompare.CONFIG_CSV.contentEquals(missingFormatId)) {
			r.initializeJsonFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		} else if (RobotFormatCompare.CONFIG_JSON.contentEquals(missingFormatId)) {
			r.initializeCsvFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		}

		try {
			r.compareCsvWithJson("/path/to/map.file", "json", "xml", 0);
		} catch (RobotFormatCompareException e) {
			assertEquals("The format " + missingFormatId + " is not yet initialized.", e.getMessage());
		}
	}
	
	
	@Test
	public void compareExcelWithXML_missingExcelConfig() {
		compareExcelWithXML_missingConfig(RobotFormatCompare.CONFIG_EXCEL);
	}

	@Test
	public void compareExcelWithXML_missingXmlConfig() {
		compareExcelWithXML_missingConfig(RobotFormatCompare.CONFIG_XML);
	}

	private void compareExcelWithXML_missingConfig(String missingFormatId) {
		String timezone = "GMT+01:00";
		String dateTimeFormat = "dtf";
		String dateFormat = "tf";
		String numberFormatWithBraces = "\"nf\"";

		RobotFormatCompare r = new RobotFormatCompare();

		if (RobotFormatCompare.CONFIG_EXCEL.contentEquals(missingFormatId)) {
			r.initializeXmlFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		} else if (RobotFormatCompare.CONFIG_XML.contentEquals(missingFormatId)) {
			r.initializeExcelFormatHandler(timezone, dateTimeFormat, dateFormat, numberFormatWithBraces);
		}

		try {
			r.compareExcelWithXML("/path/to/map.file", "/path/to/excel.xlsx", "xml");
		} catch (RobotFormatCompareException e) {
			assertEquals("The format " + missingFormatId + " is not yet initialized.", e.getMessage());
		}
	}
	
	
}
