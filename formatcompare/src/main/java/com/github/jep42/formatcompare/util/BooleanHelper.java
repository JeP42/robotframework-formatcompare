package com.github.jep42.formatcompare.util;

public class BooleanHelper {

    /**
     * FormatCompare supports several string representations of a boolean value.
     * The following strings lead to boolean <strong>true</strong>: "true" (case insensitive) and "1".
     * Everything else, including null, leads to boolean <strong>false</strong>.
     *
     * @param booleanAsString
     * @return
     */
    public static Boolean getBooleanFromString(String booleanAsString) {
        Boolean boolValue = new Boolean(booleanAsString);
        if (!boolValue) {
            if ("1".equals(booleanAsString)) {
                boolValue = true;
            }
        }
        return boolValue;
    }

}
