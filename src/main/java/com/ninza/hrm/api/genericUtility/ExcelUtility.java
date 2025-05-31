package com.ninza.hrm.api.genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	String path="./testdata/testData.xlsx";

public String getExcelData(String sheetName , int rowNum, int cellNum) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream(path);
	Workbook workbook = WorkbookFactory.create(fis);
	Sheet sheet = workbook.getSheet(sheetName);
	String data = sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
	return data;
}

public void writeDataIntoExcel(String SheetName, String data,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream(path);
	Workbook workbook = WorkbookFactory.create(fis);
	Sheet sheet = workbook.getSheet(SheetName);
	Cell cell = sheet.getRow(rowNum).createCell(cellNum);
	cell.setCellType(CellType.STRING);
	cell.setCellValue(data);
	FileOutputStream fos=new FileOutputStream(path);
	workbook.write(fos);
	workbook.close();
}
 
public int getRowCount(String SheetName) throws Throwable {
	FileInputStream fis = new FileInputStream(path);
	Workbook workbook = WorkbookFactory.create(fis);
	int rowCount = workbook.getSheet(SheetName).getLastRowNum();
	workbook.close();
	return rowCount;
}
}
