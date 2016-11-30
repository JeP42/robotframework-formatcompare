package com.github.jep42.formatcompare.formathandler;

import java.util.TimeZone;

import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.formathandler.impl.CSVFormatHandlerImpl;
import com.github.jep42.formatcompare.formathandler.impl.JsonFormatHandlerImpl;
import com.github.jep42.formatcompare.formathandler.impl.XMLFormatHandlerImpl;

public class FormatHandlerFactory {


	public static FormatHandler getFormatHandlerForCSV(String content, int headerLineIndex, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		return new CSVFormatHandlerImpl(content, headerLineIndex, timezone, dateTimeFormat, dateFormat, numberFormat);
	}

	public static FormatHandler getFormatHandlerForXML(String content, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		return new XMLFormatHandlerImpl(content, timezone, dateTimeFormat, dateFormat, numberFormat);
	}

	public static FormatHandler getFormatHandlerForJson(String content, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
		return new JsonFormatHandlerImpl(content, timezone, dateTimeFormat, dateFormat, numberFormat);
	}



}
