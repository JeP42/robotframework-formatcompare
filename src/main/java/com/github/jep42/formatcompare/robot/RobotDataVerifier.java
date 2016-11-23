package com.github.jep42.formatcompare.robot;

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

import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.github.jep42.formatcompare.SimpleDataVerifier;
import com.github.jep42.formatcompare.formathandler.FormatHandlerFactory;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.util.DataVerifierException;

@RobotKeywords
public class RobotDataVerifier {

	private static final String NUMBERFORMAT = "NUMBERFORMAT";

	private static final String DATEFORMAT = "DATEFORMAT";

	private static final String DATETIMEFORMAT = "DATETIMEFORMAT";

	private static final String TIMEZONE = "TIMEZONE";

	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	private static final String CONFIG_JSON = "JSON";

	private static final String CONFIG_CSV = "CSV";

	private static final String CONFIG_XML = "XMl";

	private Map<String, Map<String, String>> verfierConfigs = new ConcurrentHashMap<String, Map<String, String>>();


	@RobotKeyword("Initialize Json Verifier")
	@ArgumentNames({"usersTimezone", "usersDateTimeFormat", "usersDateFormat", "numberFormat"})
	public void initializeJsonVerifier(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_JSON, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	@RobotKeyword("Initialize Csv Verifier")
	@ArgumentNames({"usersTimezone", "usersDateTimeFormat", "usersDateFormat", "numberFormat"})
	public void initializeCsvVerifier(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_CSV, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	@RobotKeyword("Initialize Xml Verifier")
	@ArgumentNames({"usersTimezone", "usersDateTimeFormat", "usersDateFormat", "numberFormat"})
	public void initializeXmlVerifier(String timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		verfierConfigs.put(CONFIG_XML, this.getAsMap(timezone, dateTimeFormat, dateFormat, numberFormat));
	}

	@RobotKeyword("Verify Json Against Xml")
	@ArgumentNames({"mapFilePath", "jsonPath", "xmlPath"})
	public void verifyJsonAgainstXML(String mapFilePath, String json, String xml) throws DataVerifierException {
		this.verifyConfig(CONFIG_JSON);
		this.verifyConfig(CONFIG_XML);
		SimpleDataVerifier.createVerifier().verify(mapFilePath, this.getFormatHandlerforJson(this.getContent(json), CONFIG_JSON),
				this.getFormatHandlerforXml(this.getContent(xml), CONFIG_XML));
	}

	@RobotKeyword("Verify Json Against Xml")
	@ArgumentNames({"mapFilePath", "jsonPath", "xmlPath"})
	public void verifyJsonObjectAgainstXML(String mapFilePath, String json, String xml) throws DataVerifierException {
		this.verifyConfig(CONFIG_JSON);
		this.verifyConfig(CONFIG_XML);
		SimpleDataVerifier.createVerifier().verify(mapFilePath, this.getFormatHandlerforJson(this.getContent(json), CONFIG_JSON),
				this.getFormatHandlerforXml(this.getContent(xml), CONFIG_XML));
	}

	@RobotKeyword("Verify Csv Against Xml")
	@ArgumentNames({"mapFilePath", "csvPath", "xmlPath", "csvHeaderLineIndex"})
	public void verifyCsvAgainstXML(String mapFilePath, String csv, String xml, int csvHeaderLineIndex) throws DataVerifierException {
		this.verifyConfig(CONFIG_CSV);
		this.verifyConfig(CONFIG_XML);
		SimpleDataVerifier.createVerifier().verify(mapFilePath, this.getFormatHandlerforCsv(this.getContent(csv), CONFIG_CSV, csvHeaderLineIndex),
				this.getFormatHandlerforXml(this.getContent(xml), CONFIG_XML));
	}

	@RobotKeyword("Verify Csv Against Json")
	@ArgumentNames({"mapFilePath", "csvPath", "jsonPath", "csvHeaderLineIndex"})
	public void verifyCsvAgainstJson(String mapFilePath, String csv, String json, int csvHeaderLineIndex) throws DataVerifierException {
		this.verifyConfig(CONFIG_CSV);
		this.verifyConfig(CONFIG_JSON);
		SimpleDataVerifier.createVerifier().verify(mapFilePath, this.getFormatHandlerforCsv(this.getContent(csv), CONFIG_CSV, csvHeaderLineIndex),
				this.getFormatHandlerforJson(this.getContent(json), CONFIG_JSON));
	}


	private void verifyConfig(String formatKey) {
		if (this.verfierConfigs.get(formatKey) == null) {
			throw new RobotDataVerifierException("The format " + formatKey + " is not yet initialized.");
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
