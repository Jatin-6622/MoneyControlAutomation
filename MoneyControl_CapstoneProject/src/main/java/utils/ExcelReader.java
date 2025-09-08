package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    // Constructor to load Excel file and sheet
    public ExcelReader(String filePath, String sheetName) {
        try {
            this.filePath = filePath;
            FileInputStream fis = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get row count
    public int getRowCount() {
        return sheet.getLastRowNum();
    }
    public int getColumnCount(int rowNum) {
        Row row = sheet.getRow(rowNum);
        if(row != null) {
            return row.getLastCellNum();
        }
        return 0;
    }

    // Get cell data as String
    public String getCellData(int rowNum, int colNum) {
        try {
            Row row = sheet.getRow(rowNum);
            if(row != null) {
                Cell cell = row.getCell(colNum);
                if(cell != null) {
                    return cell.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public void setCellData(int rowNum, int colNum, String value) {
        try {
            Row row = sheet.getRow(rowNum);
            if(row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = row.getCell(colNum);
            if(cell == null) {
                cell = row.createCell(colNum);
            }
            cell.setCellValue(value);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int findRowByValue(int col, String value) {
        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // assuming row 0 is header
            if (sheet.getRow(i).getCell(col).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return -1; // not found
    }
    public void closeWorkbook() {
        try {
            if(workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
