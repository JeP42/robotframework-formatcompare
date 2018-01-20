package com.github.jep42.formatcompare.formathandler.impl.excel;

public interface ExcelSelector {

    /**
     * get the sheet by index
     * @return
     */
    int getSheet();

    /**
     * get the row by index
     * @return
     */
    int getRow();

    /**
     * get the column by index
     * @return
     */
    int getColumn();

}
