package it.pkg.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelFile {
	static FileInputStream fis = null;
	static XSSFWorkbook workbook = null;
	static XSSFSheet worksheet = null;

	/**
	 * 
	 * @param filePath The constructor takes file path as the parameter, reads the
	 *                 file and initializes the workbook
	 */

	public ReadExcelFile(String filepath,int sheetindex) {
		try {
			fis = new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e) {
			System.out.println("Sorry! File not Found.");
			e.printStackTrace();
		}
		// Class used to read excel file and read the data
		try {
			workbook = new XSSFWorkbook(fis);

		} catch (IOException e) {
			System.out.println("File path not found");
			e.printStackTrace();
		}
		worksheet = workbook.getSheetAt(sheetindex);
	}

	/*
	 * In this method i'm reading excel file using dependency of apache-poi
	 */

	public Iterator<Row> getData() throws IOException {

		// iterating through rows and getting row number
		Iterator<Row> rows = worksheet.iterator();
		return rows;
	}

	public int getNoOfRows() {
		int rowIndex = worksheet.getLastRowNum();

		return rowIndex + 1;
	}

	public int getNoOfColumns() {
		Iterator<Row> rowIterator = worksheet.rowIterator();
		int columnIndex = 0;
		/**
		 * Escape the header row *
		 */
		if (rowIterator.hasNext()) {
			Row headerRow = rowIterator.next();
			// get the number of cells in the header row
			columnIndex = headerRow.getPhysicalNumberOfCells();
		}
		return columnIndex;
	}
	
	// returns the row count in a sheet

	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			worksheet = workbook.getSheetAt(index);
			int number = worksheet.getLastRowNum() + 1;
			return number;
		}

	}
	
	public ReadExcelFile(String filepath,String sheetname) {
		try {
			fis = new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e) {
			System.out.println("Sorry! File not Found.");
			e.printStackTrace();
		}
		// Class used to read excel file and read the data
		try {
			workbook = new XSSFWorkbook(fis);

		} catch (IOException e) {
			System.out.println("File path not found");
			e.printStackTrace();
		}
		int index = workbook.getSheetIndex(sheetname);
		worksheet = workbook.getSheetAt(index);
	}

	
	

}
