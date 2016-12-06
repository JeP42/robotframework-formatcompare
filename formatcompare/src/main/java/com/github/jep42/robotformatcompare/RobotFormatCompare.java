package com.github.jep42.robotformatcompare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import com.github.jep42.formatcompare.FormatComparator;
import com.github.jep42.formatcompare.formathandler.FormatHandlerFactory;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;

/**
 *
 * This library provides keywords to compare different data structures (csv, xml, json) via mapfiles.
 * The mapfile specifies the elements which are compared with each other and defines certain rules for this compare operation.
 *
 * == MapFile format ==
 *
 * The format of the mapfile is CSV and has the following structure:
 * _*<masterElementSelectorExpression>;<slaveElementSelectorExpression>;<dataType>;<condition>;<masterOptions>;<slaveOptions>*_
 *
 * === Selector Expressions ===
 *
 * The _<masterElementSelectorExpression>_ selects an element in the master file/structure. This element is mapped to the element
 * in the slave file/structure which can be selected via _<slaveElementSelectorExpression>_. The concrete selector expression depends
 * on the format of the file:
 *
 * | =Format= | =Selector= | =Reference= |
 * | XML | XPath expression | https://www.w3.org/TR/xpath/ |
 * | JSON | JsonPath expression | http://goessner.net/articles/JsonPath/ |
 * | CSV | EasyCSVMap selector expression | https://github.com/JeP42/robotframework-easycsvmap |
 *
 * === Data Types ===
 *
 * A data type has to be specified for the mapped elements. The following data types are supported:
 *
 * | =Data type= | =Description= |
 * | STRING | Compares values as strings |
 * | INTEGER | Compares values as integers |
 * | DECIMAL | Compares values as decimal values considering the given decimal number format pattern |
 * | DATE | Compares values as date values considering the given date pattern |
 * | DATETIME | Compares values as date values considering the given date-time pattern |
 *
 * === Condition ===
 *
 * Depending on the format there are different conditions supported for the compare operation. The evaluation of the specified
 * condition must be "true" otherwise the compare operation will fail.
 *
 * _Conditions for data type *STRING*_
 *
 * | =Compare operation= | =Meaning= | =Description= |
 * | = | equal | values are checked to be equal (case sensitive) |
 * | != | unequal | values are checked to be unequal |
 * | & | contains | the value of the master element must contain the element of the slave element. Therefore, the slave value is a substring of the master value. |
 * | $ | contained | the value of the slave element must contain the element of the master element. Therefore, the master value is a substring of the slave value. |
 *
 *
 * _Conditions for numeric data types *DECIMAL* and *INTEGER*_
 *
 * | =Compare operation= | =Meaning= | =Description= |
 * | = | equal | values are checked to be equal |
 * | != | unequal | values are checked to be unequal |
 * | < | smaller | The master value is checked to be smaller than the slave value (master < slave) |
 * | > | greater | The master value is checked to be greater than the slave value (master > slave) |
 *
 *
 * _Conditions for date data types *DATE* and *DATETIME*_
 *
 * | =Compare operation= | =Meaning= | =Description= |
 * | = | equal | Both date values are checked to be equal considering the given date pattern |
 * | < | before | The master date value is checked to be before the slave slave date value (master < slave) |
 * | > | after | The master date value is checked to be after the slave value (master > slave) |
 *
 *
 * === Options ===
 *
 * Options can be used to apply specific data modifications to the values before these are compared with each other. For the
 * moment there is only one option supported.
 *
 * | =Option= | =Valid for data type= | =Description= |
 * | SETTIMETOENDOFDAY | DATE, DATETIME | Set the time part of the given date value to 23:59:59:999 |
 *
 * == Patterns ==
 *
 * Patterns are passed to the format handler components to allow comparing values even if these values have deviating formats. E.g. in XML the
 * date format "20040516" may be used whereas in JSON the date is formatted as "2004-05-16". To be able to compare these values properly the date pattern must be
 * known so that the comparator can parse date values from the date strings and use these date values for comparison.
 *
 *
 * === Date and Time patterns ===
 *
 * For date and time patterns the standard Java pattern syntax is used. Details about the syntax can be found in the Javadocs: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
 * Here some examples:
 *
 * | =Pattern= | =Formatted date= |
 * | dd.MM.yyyy | 16.05.2004  |
 * | yyyyMMdd | 20040516  |
 * | dd-MM-yyyy HH:mm:ss | 16-05-2004 12:34:56 |
 *
 *
 * === Decimal number format pattern ===
 *
 * The format pattern consists of three characters enclosed in double quotes. The first character specifies the grouping separator, the second the decimal separator and the last the number of decimal digits: _*"<groupingSeparator><decimalSeparator><numberOfDecimalDigits>"*_.
 * Here some examples for the numeric values 1234,42:
 *
 * | =Pattern= | =Formatted number= |
 * | ".,2" | 1.234,42 |
 * | ",.2" | 1,234.42 |
 * | " ,2" | 1 234,42 |
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

	private Map<String, Map<String, String>> verfierConfigs = new ConcurrentHashMap<>();

	/**
	 * Initialize Json format handler
	 *
	 * Arguments:
	 * - _timezone_: User timezone to parse date strings.
	 * - _dateTimeFormat_: Datetime format pattern
	 * - _dateFormat_: Date format pattern
	 * - _numberFormat_: Number format pattern for decimal values. More details about the decimal format pattern can be found at the top of the page.
	 *
	 * Example:
	 * | Initialize Json Format Handler | GMT+01:00 | dd.MM.yyyy HH:mm:ss | dd.MM.yyyy | " ,2" |
	 *
	 */
	public void initializeJsonFormatHandler(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_JSON, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	/**
	 * Initialize Csv format handler
	 *
	 * Arguments:
	 * - _timezone_: User timezone to parse date strings.
	 * - _dateTimeFormat_: Datetime format pattern
	 * - _dateFormat_: Date format pattern
	 * - _numberFormat_: Number format pattern for decimal values. More details about the decimal format pattern can be found at the top of the page.
	 *
	 * Example:
	 * | Initialize Csv Format Handler | GMT+01:00 | dd.MM.yyyy HH:mm:ss | dd.MM.yyyy | " ,2" |
	 *
	 */
	public void initializeCsvFormatHandler(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_CSV, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	/**
	 * Initialize Xml format handler
	 *
	 * Arguments:
	 * - _timezone_: User timezone to parse date strings.
	 * - _dateTimeFormat_: Datetime format pattern
	 * - _dateFormat_: Date format pattern
	 * - _numberFormat_: Number format pattern for decimal values. More details about the decimal format pattern can be found at the top of the page.
	 *
	 * Example:
	 * | Initialize Xml Format Handler | GMT+01:00 | dd.MM.yyyy HH:mm:ss | dd.MM.yyyy | " ,2" |
	 *
	 */
	public void initializeXmlFormatHandler(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_XML, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	/**
	 *
	 * Verify Json With Xml
	 *
	 * Arguments:
	 * - _mapFilePath_: Path to the map file
	 * - _json_: JSON object or path to JSON file
	 * - _xml_: XML object or path to XML file
	 *
	 *
	 * Example:
	 * | Verify Json With XML | ./data.mapfile | ${jsonObject} | ${TEMPDIR}/download.xml |
	 *
	 *
	 */
	public void compareJsonWithXML(String mapFilePath, String json, String xml) {
		this.verifyConfig(CONFIG_JSON);
		this.verifyConfig(CONFIG_XML);
		FormatComparator.createComparator().compare(mapFilePath, this.getFormatHandlerforJson(this.getContent(json), CONFIG_JSON),
				this.getFormatHandlerforXml(this.getContent(xml), CONFIG_XML));
	}


    /**
     * Verify Csv With Xml
     *
     * Arguments:
	 * - _mapFilePath_: Path to the map file
	 * - _csv_: CSV object or path to CSV file
	 * - _xml_: XML object or path to XML file
	 * - _csvHeaderLineIndex_: Index of the CSV header line. Set to -1 if the CSV does not contain a header line.
     *
	 *
	 * Example:
	 * | Verify Csv With XML | ./data.mapfile | ${csvObject} | ${TEMPDIR}/download.xml |
	 *
     */
	public void compareCsvWithXML(String mapFilePath, String csv, String xml, int csvHeaderLineIndex) {
		this.verifyConfig(CONFIG_CSV);
		this.verifyConfig(CONFIG_XML);
		FormatComparator.createComparator().compare(mapFilePath, this.getFormatHandlerforCsv(this.getContent(csv), CONFIG_CSV, csvHeaderLineIndex),
				this.getFormatHandlerforXml(this.getContent(xml), CONFIG_XML));
	}

	/**
	 * Verify Csv With Json
	 *
	 * Arguments:
	 * - _mapFilePath_: Path to the map file
	 * - _csv_: CSV object or path to CSV file
	 * - _json_: JSON object or path to JSON file
	 * - _csvHeaderLineIndex_: Index of the CSV header line. Set to -1 if the CSV does not contain a header line.
     *
	 *
	 * Example:
	 * | Verify Csv With Json | ./data.mapfile | ${csvObject} | ${TEMPDIR}/download.json |
	 *
	 */
	public void compareCsvWithJson(String mapFilePath, String csv, String json, int csvHeaderLineIndex) {
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
		return FormatHandlerFactory.getFormatHandlerForJson(this.getContent(json),
				TimeZone.getTimeZone(this.verfierConfigs.get(formatKey).get(TIMEZONE)),
				this.verfierConfigs.get(formatKey).get(DATETIMEFORMAT),
				this.verfierConfigs.get(formatKey).get(DATEFORMAT),
				this.verfierConfigs.get(formatKey).get(NUMBERFORMAT));
	}

	private FormatHandler getFormatHandlerforXml(String xml, String formatKey) {
		return FormatHandlerFactory.getFormatHandlerForXML(this.getContent(xml),
				TimeZone.getTimeZone(this.verfierConfigs.get(formatKey).get(TIMEZONE)),
				this.verfierConfigs.get(formatKey).get(DATETIMEFORMAT),
				this.verfierConfigs.get(formatKey).get(DATEFORMAT),
				this.verfierConfigs.get(formatKey).get(NUMBERFORMAT));
	}

	private FormatHandler getFormatHandlerforCsv(String csv, String formatKey, int headerLineIndex) {
		return FormatHandlerFactory.getFormatHandlerForCSV(this.getContent(csv), headerLineIndex,
				TimeZone.getTimeZone(this.verfierConfigs.get(formatKey).get(TIMEZONE)),
				this.verfierConfigs.get(formatKey).get(DATETIMEFORMAT),
				this.verfierConfigs.get(formatKey).get(DATEFORMAT),
				this.verfierConfigs.get(formatKey).get(NUMBERFORMAT));
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
			return Paths.get(json).toFile().exists();
		} catch (InvalidPathException e) {
			return false;
		}
	}

	private Map<String, String> getAsMap(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		Map<String, String> map = new ConcurrentHashMap<>();
		map.put(TIMEZONE, timezone);
		map.put(DATETIMEFORMAT, dateTimeFormat);
		map.put(DATEFORMAT, dateFormat);
		//to support leading blank in format pattern the parameter numberFormat has to be enclosed in braces in robot test case. These braces have to be
		//removed before passing the pattern string to the DataVerifier
		map.put(NUMBERFORMAT, numberFormat.split("\"")[1]);
		return map;
	}

	private String loadFile(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath)) ) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		} catch (IOException e) {
			throw new RobotFormatCompareException(e);
		}
	}

}
