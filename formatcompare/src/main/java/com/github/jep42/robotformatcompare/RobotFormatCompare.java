package com.github.jep42.robotformatcompare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import com.github.jep42.formatcompare.FormatComparator;
import com.github.jep42.formatcompare.formathandler.FormatHandlerFactory;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.FormatComparatorException;

/**
 *
 * This library provides keywords to compare different data structures (csv, xml, json) via mapfiles
 *
 * Decimal number format pattern
 * The format pattern always consists of three characters: the first specifies the grouping separator, the second the decimal separator and the last the number of decimal digits.
 *
 */
public class RobotFormatCompare {

	public static final String ROBOT_LIBRARY_VERSION = "0.1";

	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	private static final String NUMBERFORMAT = "NUMBERFORMAT";

	private static final String DATEFORMAT = "DATEFORMAT";

	private static final String DATETIMEFORMAT = "DATETIMEFORMAT";

	private static final String TIMEZONE = "TIMEZONE";

	private static final String CONFIG_JSON = "JSON";

	private static final String CONFIG_CSV = "CSV";

	private static final String CONFIG_XML = "XMl";

	private Map<String, Map<String, String>> verfierConfigs = new ConcurrentHashMap<String, Map<String, String>>();

	/**
	 * Initialize Json Verifier
	 *
	 * Arguments:
	 * - _timezone_: User timezone to parse date strings.
	 * - _dateTimeFormat_: Datetime format pattern
	 * - _dateFormat_: Date format pattern
	 * - _numberFormat_: Number format pattern for decimal values. More details about the decimal format pattern can be found at the top of the page.
	 *
	 * Example:
	 * | Initialize Json Verifier | GMT+01:00 | dd.MM.yyyy HH:mm:ss | dd.MM.yyyy | " ,2" |
	 *
	 */
	public void initializeJsonVerifier(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_JSON, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	/**
	 * Initialize Csv Verifier
	 *
	 * Arguments:
	 * - _timezone_: User timezone to parse date strings.
	 * - _dateTimeFormat_: Datetime format pattern
	 * - _dateFormat_: Date format pattern
	 * - _numberFormat_: Number format pattern for decimal values. More details about the decimal format pattern can be found at the top of the page.
	 *
	 * Example:
	 * | Initialize Csv Verifier | GMT+01:00 | dd.MM.yyyy HH:mm:ss | dd.MM.yyyy | " ,2" |
	 *
	 */
	public void initializeCsvVerifier(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_CSV, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	/**
	 * Initialize Xml Verifier
	 *
	 * Arguments:
	 * - _timezone_: User timezone to parse date strings.
	 * - _dateTimeFormat_: Datetime format pattern
	 * - _dateFormat_: Date format pattern
	 * - _numberFormat_: Number format pattern for decimal values. More details about the decimal format pattern can be found at the top of the page.
	 *
	 * Example:
	 * | Initialize Xml Verifier | GMT+01:00 | dd.MM.yyyy HH:mm:ss | dd.MM.yyyy | " ,2" |
	 *
	 */
	public void initializeXmlVerifier(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_XML, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	/**
	 *
	 * Verify Json Against Xml
	 *
	 * Arguments:
	 * - _mapFilePath_: Path to the map file
	 * - _json_: JSON object or path to JSON file
	 * - _xml_: XML object or path to XML file
	 *
	 * Throws:
	 * - _FormatComparatorException_
	 *
	 * Example:
	 * | Verify Json Against XML | ./data.mapfile | ${jsonObject} | ${TEMPDIR}/download.xml |
	 *
	 *
	 */
	public void verifyJsonAgainstXML(String mapFilePath, String json, String xml) throws FormatComparatorException {
		this.verifyConfig(CONFIG_JSON);
		this.verifyConfig(CONFIG_XML);
		FormatComparator.createComparator().compare(mapFilePath, this.getFormatHandlerforJson(this.getContent(json), CONFIG_JSON),
				this.getFormatHandlerforXml(this.getContent(xml), CONFIG_XML));
	}


    /**
     * Verify Csv Against Xml
     *
     * Arguments:
	 * - _mapFilePath_: Path to the map file
	 * - _csv_: CSV object or path to CSV file
	 * - _xml_: XML object or path to XML file
	 * - _csvHeaderLineIndex_: Index of the CSV header line. Set to -1 if the CSV does not contain a header line.
     *
     * Throws:
	 * - _FormatComparatorException_
	 *
	 * Example:
	 * | Verify Csv Against XML | ./data.mapfile | ${csvObject} | ${TEMPDIR}/download.xml |
	 *
     */
	public void verifyCsvAgainstXML(String mapFilePath, String csv, String xml, int csvHeaderLineIndex) throws FormatComparatorException {
		this.verifyConfig(CONFIG_CSV);
		this.verifyConfig(CONFIG_XML);
		FormatComparator.createComparator().compare(mapFilePath, this.getFormatHandlerforCsv(this.getContent(csv), CONFIG_CSV, csvHeaderLineIndex),
				this.getFormatHandlerforXml(this.getContent(xml), CONFIG_XML));
	}

	/**
	 * Verify Csv Against Json
	 *
	 * Arguments:
	 * - _mapFilePath_: Path to the map file
	 * - _csv_: CSV object or path to CSV file
	 * - _json_: JSON object or path to JSON file
	 * - _csvHeaderLineIndex_: Index of the CSV header line. Set to -1 if the CSV does not contain a header line.
     *
     * Throws:
	 * - _FormatComparatorException_
	 *
	 * Example:
	 * | Verify Csv Against Json | ./data.mapfile | ${csvObject} | ${TEMPDIR}/download.json |
	 *
	 */
	public void verifyCsvAgainstJson(String mapFilePath, String csv, String json, int csvHeaderLineIndex) throws FormatComparatorException {
		this.verifyConfig(CONFIG_CSV);
		this.verifyConfig(CONFIG_JSON);
		FormatComparator.createComparator().compare(mapFilePath, this.getFormatHandlerforCsv(this.getContent(csv), CONFIG_CSV, csvHeaderLineIndex),
				this.getFormatHandlerforJson(this.getContent(json), CONFIG_JSON));
	}


	private void verifyConfig(String formatKey) {
		if (this.verfierConfigs.get(formatKey) == null) {
			throw new RobotFormatCompareException("The format " + formatKey + " is not yet initialized.");
		}
	}

	private FormatHandler getFormatHandlerforJson(String json, String formatKey) {
		FormatHandler formatHandler = FormatHandlerFactory.getFormatHandlerForJson(this.getContent(json),
				TimeZone.getTimeZone(this.verfierConfigs.get(formatKey).get(TIMEZONE)),
				this.verfierConfigs.get(formatKey).get(DATETIMEFORMAT),
				this.verfierConfigs.get(formatKey).get(DATEFORMAT),
				this.verfierConfigs.get(formatKey).get(NUMBERFORMAT));
		return formatHandler;
	}

	private FormatHandler getFormatHandlerforXml(String xml, String formatKey) {
		FormatHandler formatHandler = FormatHandlerFactory.getFormatHandlerForXML(this.getContent(xml),
				TimeZone.getTimeZone(this.verfierConfigs.get(formatKey).get(TIMEZONE)),
				this.verfierConfigs.get(formatKey).get(DATETIMEFORMAT),
				this.verfierConfigs.get(formatKey).get(DATEFORMAT),
				this.verfierConfigs.get(formatKey).get(NUMBERFORMAT));
		return formatHandler;
	}

	private FormatHandler getFormatHandlerforCsv(String csv, String formatKey, int headerLineIndex) {
		FormatHandler formatHandler = FormatHandlerFactory.getFormatHandlerForCSV(this.getContent(csv), headerLineIndex,
				TimeZone.getTimeZone(this.verfierConfigs.get(formatKey).get(TIMEZONE)),
				this.verfierConfigs.get(formatKey).get(DATETIMEFORMAT),
				this.verfierConfigs.get(formatKey).get(DATEFORMAT),
				this.verfierConfigs.get(formatKey).get(NUMBERFORMAT));
		return formatHandler;
	}


	/**
	 * Incoming string is either file path where we can read content from or it already is content
	 *
	 * @param json
	 * @return
	 */
	private String getContent(String json) {
		String fileContent = json;
		if (this.isValidFilePath(json)) {
			fileContent  = this.loadFile(json);
		}
		return fileContent;
	}

	private boolean isValidFilePath(String json) {
		try {
			return Files.exists(Paths.get(json));
		} catch (InvalidPathException e) {
			return false;
		}
	}

	private Map<String, String> getAsMap(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		map.put(TIMEZONE, timezone);
		map.put(DATETIMEFORMAT, dateTimeFormat);
		map.put(DATEFORMAT, dateFormat);
		//to support leading blank in format pattern the parameter numberFormat has to be enclosed in braces in robot test case. These braces have to be
		//removed before passing the pattern string to the DataVerifier
		map.put(NUMBERFORMAT, numberFormat.split("\"")[1]);
		return map;
	}

	private String loadFile(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));

		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			this.closeStream(br);
		}
	}

	private void closeStream(Reader reader) {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
