package com.github.jep42.formatcompare;

import java.io.File;
import java.net.URL;
import java.util.TimeZone;

import org.junit.Test;

import com.github.jep42.formatcompare.formathandler.FormatHandlerFactory;
import com.github.jep42.formatcompare.formathandler.api.FormatHandler;


public class FormatComparatorExcel2XmlTest {

    private static final String  TIMEZONE_PLUS_ONE_SHORT = "GMT+01:00";


    private static final String  DATE_TIME_FORMAT_PATTERN_DE_XML = "yyyyMMddHHmmss";

    private static final String  DATE_TIME_FORMAT_PATTERN_DE_EXCEL = "yyyyMMddHHmmss";


    private static final String  DATE_FORMAT_PATTERN_DE_XML = "yyyyMMddHHmmss";

    private static final String  DATE_FORMAT_PATTERN_DE_EXCEL = "yyyyMMddHHmmss";


    private static final String  NUMBER_FORMAT_PATTERN_DE_XML = " .2";

    private static final String  NUMBER_FORMAT_PATTERN_DE_EXCEL = " ,2";


   

    
    @Test
    public void verify_xls2xml_numeric() throws Exception {
    	verify_excel2xml_numeric("com/github/jep42/formatcompare/numeric/numeric_test.xls");
    }
    
    @Test
    public void verify_xlsx2xml_numeric() throws Exception {
    	verify_excel2xml_numeric("com/github/jep42/formatcompare/numeric/numeric_test.xlsx");
    }
    
    private void verify_excel2xml_numeric(String excelFile) throws Exception {
    	File xlsFile = this.getFile(excelFile);
        String xmlString = UnitTestUtil.getFile("com/github/jep42/formatcompare/numeric/numeric_test.xml");
        String mapFilePath = this.getMapFilePath("com/github/jep42/formatcompare/numeric/numeric_excel2xml.mapfile");

        FormatHandler formatHandlerForXML = FormatHandlerFactory.getFormatHandlerForXML(xmlString, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_XML, DATE_FORMAT_PATTERN_DE_XML, NUMBER_FORMAT_PATTERN_DE_XML);
        FormatHandler formatHandlerForExcel = FormatHandlerFactory.getFormatHandlerForExcel(xlsFile, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_EXCEL, DATE_FORMAT_PATTERN_DE_EXCEL, NUMBER_FORMAT_PATTERN_DE_EXCEL);

        FormatComparator.createComparator().compare(mapFilePath, formatHandlerForExcel, formatHandlerForXML);
    }
    

    @Test
    public void verify_xls2xml_string() throws Exception {
    	verify_excel2xml_string("com/github/jep42/formatcompare/string/string_test.xls");
    }
    
    @Test
    public void verify_xlsx2xml_string() throws Exception {
    	verify_excel2xml_string("com/github/jep42/formatcompare/string/string_test.xlsx");
    }
    
    private void verify_excel2xml_string(String excelFile) throws Exception {
    	File xlsFile = this.getFile(excelFile);
    	
        String xmlString = UnitTestUtil.getFile("com/github/jep42/formatcompare/string/string_test.xml");
        String mapFilePath = this.getMapFilePath("com/github/jep42/formatcompare/string/string_excel2xml.mapfile");
        FormatHandler formatHandlerForXML = FormatHandlerFactory.getFormatHandlerForXML(xmlString, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_XML, DATE_FORMAT_PATTERN_DE_XML, NUMBER_FORMAT_PATTERN_DE_XML);
        FormatHandler formatHandlerForExcel = FormatHandlerFactory.getFormatHandlerForExcel(xlsFile, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_EXCEL, DATE_FORMAT_PATTERN_DE_EXCEL, NUMBER_FORMAT_PATTERN_DE_EXCEL);

        FormatComparator.createComparator().compare(mapFilePath, formatHandlerForExcel, formatHandlerForXML);
    }
    
    
    @Test
    public void verify_xls2xml_multisheet() throws Exception {
    	verify_excel2xml_multisheet("com/github/jep42/formatcompare/string/string_test_multisheet.xls");
    }
    
    @Test
    public void verify_xlsx2xml_multisheet() throws Exception {
    	verify_excel2xml_multisheet("com/github/jep42/formatcompare/string/string_test_multisheet.xlsx");
    }
    
    
    private void verify_excel2xml_multisheet(String excelFile) throws Exception {
    	File xlsFile = this.getFile(excelFile);
    	
        String xmlString = UnitTestUtil.getFile("com/github/jep42/formatcompare/string/string_test.xml");
        String mapFilePath = this.getMapFilePath("com/github/jep42/formatcompare/string/string_excel2xml_multisheet.mapfile");
        
        FormatHandler formatHandlerForXML = FormatHandlerFactory.getFormatHandlerForXML(xmlString, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_XML, DATE_FORMAT_PATTERN_DE_XML, NUMBER_FORMAT_PATTERN_DE_XML);
        FormatHandler formatHandlerForExcel = FormatHandlerFactory.getFormatHandlerForExcel(xlsFile, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_EXCEL, DATE_FORMAT_PATTERN_DE_EXCEL, NUMBER_FORMAT_PATTERN_DE_EXCEL);

        FormatComparator.createComparator().compare(mapFilePath, formatHandlerForExcel, formatHandlerForXML);
    }
    
   
    @Test
    public void verify_xls2xml_date() throws Exception {
    	verify_excel2xml_date("com/github/jep42/formatcompare/date/date_test.xls");
    }
    
    @Test
    public void verify_xlsx2xml_date() throws Exception {
    	verify_excel2xml_date("com/github/jep42/formatcompare/date/date_test.xlsx");
    }
    
    private void verify_excel2xml_date(String excelFile) throws Exception {
    	File xlsFile = this.getFile(excelFile);
        String xmlString = UnitTestUtil.getFile("com/github/jep42/formatcompare/date/date_test.xml");
        String mapFilePath = this.getMapFilePath("com/github/jep42/formatcompare/date/date_excel2xml.mapfile");

        FormatHandler formatHandlerForXML = FormatHandlerFactory.getFormatHandlerForXML(xmlString, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_XML, DATE_FORMAT_PATTERN_DE_XML, NUMBER_FORMAT_PATTERN_DE_XML);
        FormatHandler formatHandlerForExcel = FormatHandlerFactory.getFormatHandlerForExcel(xlsFile, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_EXCEL, DATE_FORMAT_PATTERN_DE_EXCEL, NUMBER_FORMAT_PATTERN_DE_EXCEL);

        FormatComparator.createComparator().compare(mapFilePath, formatHandlerForExcel, formatHandlerForXML);
    }

    
    @Test
    public void verify_xls2xml_boolean() throws Exception {
    	verify_xls2xml_boolean("com/github/jep42/formatcompare/boolean/boolean_test.xls");
    }
    
    @Test
    public void verify_xlsx2xml_boolean() throws Exception {
    	verify_xls2xml_boolean("com/github/jep42/formatcompare/boolean/boolean_test.xlsx");
    }
    
    private void verify_xls2xml_boolean(String excelFile) throws Exception {
    	File xlsFile = this.getFile(excelFile);
        String xmlString = UnitTestUtil.getFile("com/github/jep42/formatcompare/boolean/boolean_test.xml");
        String mapFilePath = this.getMapFilePath("com/github/jep42/formatcompare/boolean/boolean_xls2xml.mapfile");

        FormatHandler formatHandlerForXML = FormatHandlerFactory.getFormatHandlerForXML(xmlString, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_XML, DATE_FORMAT_PATTERN_DE_XML, NUMBER_FORMAT_PATTERN_DE_XML);
        FormatHandler formatHandlerForExcel = FormatHandlerFactory.getFormatHandlerForExcel(xlsFile, TimeZone.getTimeZone(TIMEZONE_PLUS_ONE_SHORT), DATE_TIME_FORMAT_PATTERN_DE_EXCEL, DATE_FORMAT_PATTERN_DE_EXCEL, NUMBER_FORMAT_PATTERN_DE_EXCEL);

        FormatComparator.createComparator().compare(mapFilePath, formatHandlerForExcel, formatHandlerForXML);
    }
    
   
    private File getFile(String path) throws Exception {
        URL resource = getClass().getClassLoader().getResource(path);
        return (new File(resource.toURI()));
    }
    
    
    private String getMapFilePath(String mapFile) throws Exception {
        URL resource = getClass().getClassLoader().getResource(mapFile);
        return (new File(resource.toURI())).getAbsolutePath();
    }

}
