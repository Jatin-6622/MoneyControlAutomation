package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
    public String getCellData(int row, int col) {
        Cell cell = sheet.getRow(row).getCell(col);
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case NUMERIC:
                if (cell.getNumericCellValue() % 1 == 0) {
                    return String.valueOf((long) cell.getNumericCellValue()); // remove .0
                }
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue().trim();
            default:
                return cell.toString().trim();
        }
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
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {  // start at 0
            if (sheet.getRow(i) == null || sheet.getRow(i).getCell(col) == null) {
                continue;
            }

            Cell cell = sheet.getRow(i).getCell(col);

            // Handle numeric cells
            if (cell.getCellType() == CellType.NUMERIC) {
                double excelVal = cell.getNumericCellValue();

                try {
                    double inputVal = Double.parseDouble(value);

                    // Round both to 2 decimals for comparison
                    double roundedExcel = Math.round(excelVal * 100.0) / 100.0;
                    double roundedInput = Math.round(inputVal * 100.0) / 100.0;

                    if (roundedExcel == roundedInput) {
                        return i;
                    }
                } catch (NumberFormatException e) {
                    // If parsing fails, skip to string comparison
                }

            } else {
                // For non-numeric (String) cells
                String cellValue = cell.toString().trim();
                if (cellValue.equalsIgnoreCase(value.trim())) {
                    return i;
                }
            }
        }
        return -1;  // not found
    }

    }

