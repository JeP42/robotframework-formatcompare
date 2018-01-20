package com.github.jep42.formatcompare.formathandler.impl.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.github.jep42.formatcompare.util.FormatComparatorException;


public class MsExcelAccessor {

    private Workbook workbook;
    private FormulaEvaluator evaluator;
    private DataFormatter formatter;

    public static MsExcelAccessor getInstance(File excelFile) {
        return new MsExcelAccessor(excelFile);
    }

    private MsExcelAccessor(File excelFile) {
        super();
        this.initializeWorkbook(excelFile);
    }


    private void initializeWorkbook(File excelFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(excelFile);
            this.workbook = WorkbookFactory.create(fis);
            this.evaluator = this.workbook.getCreationHelper().createFormulaEvaluator();
            this.formatter = new DataFormatter(true);
        } catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
            throw new FormatComparatorException("An unexpected exception occured while initializing Excel workbook", e);
        } finally {
            this.closeInputStream(fis);
        }
    }

    private void closeInputStream(FileInputStream fis) {
        if(fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                throw new FormatComparatorException("An unexpected exception occured while closing input stream", e);
            }
        }
    }

    public String getStringValueVia(String selector) {
        Cell cell = getCellBySelector(selector);
        if (cell.getCellTypeEnum() != CellType.FORMULA) {
        	cell.setCellType(CellType.STRING);
        	return cell.getStringCellValue();
        } else {
            return this.formatter.formatCellValue(cell, this.evaluator);
        }
    }


    private Cell getCellBySelector(String selector) {
        ExcelSelector excelSelector = ExcelSelectorFactory.getExcelSelectorByString(selector);

        Sheet sheet = this.workbook.getSheetAt(excelSelector.getSheet());
        Row row = sheet.getRow(excelSelector.getRow());
        Cell cell = row.getCell(excelSelector.getColumn());
        return cell;
    }

}
