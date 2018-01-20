package com.github.jep42.formatcompare.formathandler.impl.excel;

import com.github.jep42.formatcompare.util.FormatComparatorException;

public class SimpleExcelSelector implements ExcelSelector {

    private static final String EXCEPTION_MSG_MISSING_SELECTOR = "Please provide an excel selector";

    private static final String EXCEPTION_MSG_INVALID_SELECTOR_FORMAT = "Please provide a valid excel selector in the format <sheetIndex>%s<rowIndex>%s<columnIndex>";

    private static final String SELECTOR_FRAGMENT_SEPARATOR = ":";


    private int sheet;

    private int row;

    private int column;



    public SimpleExcelSelector(String selector) {
        this.validateFormat(selector);

        String[] fragments = selector.split(SELECTOR_FRAGMENT_SEPARATOR);

        this.initSheet(fragments[0]);
        this.initRow(fragments[1]);
        this.initColumn(fragments[2]);
    }

    private void initColumn(String columnIndexAsString) {
        try {
            this.column = Integer.parseInt(columnIndexAsString);
        } catch (NumberFormatException e) {
            throw new FormatComparatorException(String.format(EXCEPTION_MSG_INVALID_SELECTOR_FORMAT, SELECTOR_FRAGMENT_SEPARATOR));
        }
    }

    private void initRow(String rowIndexAsString) {
        try {
            this.row = Integer.parseInt(rowIndexAsString);
        } catch (NumberFormatException e) {
            throw new FormatComparatorException(String.format(EXCEPTION_MSG_INVALID_SELECTOR_FORMAT, SELECTOR_FRAGMENT_SEPARATOR));
        }
    }

    private void initSheet(String sheetIndexAsString) {
        try {
            this.sheet = Integer.parseInt(sheetIndexAsString);
        } catch (NumberFormatException e) {
            throw new FormatComparatorException(String.format(EXCEPTION_MSG_INVALID_SELECTOR_FORMAT, SELECTOR_FRAGMENT_SEPARATOR, SELECTOR_FRAGMENT_SEPARATOR));
        }
    }

    private void validateFormat(String selector) {
        if (selector == null) {
            throw new FormatComparatorException(EXCEPTION_MSG_MISSING_SELECTOR);
        }

        if (selector.split(SELECTOR_FRAGMENT_SEPARATOR).length != 3) {
            throw new FormatComparatorException(String.format(EXCEPTION_MSG_INVALID_SELECTOR_FORMAT, SELECTOR_FRAGMENT_SEPARATOR, SELECTOR_FRAGMENT_SEPARATOR));
        }
    }

    @Override
    public int getSheet() {
        return this.sheet;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

}
