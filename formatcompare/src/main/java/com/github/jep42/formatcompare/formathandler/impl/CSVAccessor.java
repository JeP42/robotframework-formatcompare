package com.github.jep42.formatcompare.formathandler.impl;

import java.util.Iterator;

import com.github.jep42.easycsvmap.EasyCSVMap;

public final class CSVAccessor {


    private CSVAccessor() {
        super();
    }


    public static EasyCSVMap getCsvObject(String csvString, int headerLineIndex) {
        EasyCSVMap csvMap = new EasyCSVMap(headerLineIndex);
        csvMap.parseCsv(csvString);
        return csvMap;
    }


    public static String getFirstMatchingCsvValue(EasyCSVMap csvObject, String csvElementSelector) {
        Iterator<String> it = csvObject.getValues(csvElementSelector).values().iterator();
        if (it.hasNext()) {
            return it.next();
        } else {
            return null;
        }
    }





}
