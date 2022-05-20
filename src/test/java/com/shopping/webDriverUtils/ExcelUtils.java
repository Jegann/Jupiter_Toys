package com.shopping.webDriverUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Class file to read data from Excel file
 */

public class ExcelUtils {

	private Map<String, List<String>> excelData;
	private String filename = "./datafile/Shopping_Data.xlsx";

	public void readExcelFile() throws Exception {
		try {
			excelData = new HashMap<String, List<String>>();
			FileInputStream excelFile;
			excelFile = new FileInputStream(new File(filename));
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();
			int lastCol = sheet.getRow(0).getLastCellNum();
			for (int j = 0; j <= lastCol; j++) {
				String header = null;
				List<String> value = new ArrayList<String>();
				for (int i = 0; i <= lastRow; i++) {
					XSSFCell cell = sheet.getRow(i).getCell(j);
					if (cell != null) {
						if (i == 0) {
							header = cell.getStringCellValue();
						} else {
							value.add(cell.getStringCellValue());
						}
					}
				}
				excelData.put(header, value);
			}
			//workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getExcelData(String columnName) {
		if (excelData.get(columnName) != null) {
			return excelData.get(columnName);
		}
		return null;
	}
}
