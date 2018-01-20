package com.github.jep42.formatcompare.formathandler;

import java.io.File;
import java.util.TimeZone;

import com.github.jep42.formatcompare.formathandler.api.FormatHandler;
import com.github.jep42.formatcompare.formathandler.impl.csv.CSVFormatHandlerImpl;
import com.github.jep42.formatcompare.formathandler.impl.excel.MsExcelFormatHandlerImpl;
import com.github.jep42.formatcompare.formathandler.impl.json.JsonFormatHandlerImpl;
import com.github.jep42.formatcompare.formathandler.impl.xml.XMLFormatHandlerImpl;

public final class FormatHandlerFactory {



    private FormatHandlerFactory() {
        super();
    }

    public static FormatHandler getFormatHandlerForCSV(String content, int headerLineIndex, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
        return new CSVFormatHandlerImpl(content, headerLineIndex, timezone, dateTimeFormat, dateFormat, numberFormat);
    }

    public static FormatHandler getFormatHandlerForXML(String content, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
        return new XMLFormatHandlerImpl(content, timezone, dateTimeFormat, dateFormat, numberFormat);
    }

    public static FormatHandler getFormatHandlerForJson(String content, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
        return new JsonFormatHandlerImpl(content, timezone, dateTimeFormat, dateFormat, numberFormat);
    }

    public static FormatHandler getFormatHandlerForExcel(File excelFile, TimeZone timezone, String dateTimeFormat, String dateFormat, String numberFormat) {
        return new MsExcelFormatHandlerImpl(excelFile, timezone, dateTimeFormat, dateFormat, numberFormat);
    }


}
