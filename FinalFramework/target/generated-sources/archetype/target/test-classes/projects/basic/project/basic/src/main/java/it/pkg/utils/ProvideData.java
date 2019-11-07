package it.pkg.utils;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



public class ProvideData {
	Object[][] object;
	ReadExcelFile provider;

	public ProvideData(String filepath,int sheetindex) {
		provider = new ReadExcelFile(filepath,sheetindex);
	}

	public Object[][] provideData() {

		Iterator<Row> rows = null;
		try {
			rows = provider.getData();

			int noOfRows = provider.getNoOfRows();
			//System.out.println(noOfRows);
			int noOfColumns = provider.getNoOfColumns();
			//System.out.println(noOfColumns);
			object = new Object[noOfRows-1][noOfColumns];

		} catch (IOException e) {
			System.out.println("Exception");
		}
		int i = 0;
		rows.next();
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> iterator = row.cellIterator();
			int j = 0;
			while (iterator.hasNext()) {
				Cell cell = iterator.next();
				object[i][j] = cell.toString();
				j++;
			}
			i++;
		}
		return object;
	}
}
