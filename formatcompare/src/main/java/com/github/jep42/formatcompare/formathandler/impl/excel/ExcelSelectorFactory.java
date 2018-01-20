package com.github.jep42.formatcompare.formathandler.impl.excel;

public final class ExcelSelectorFactory {


    private ExcelSelectorFactory() {
        super();
    }

    public static ExcelSelector getExcelSelectorByString(String selector) {
        return new SimpleExcelSelector(selector);
    }

}
