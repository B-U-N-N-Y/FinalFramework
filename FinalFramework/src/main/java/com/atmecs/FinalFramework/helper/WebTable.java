package com.atmecs.FinalFramework.helper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class contain all the methods to handle the web table.
 * 
 * @author arjun.santra
 *
 */
public class WebTable {

	Utility utility;
	List<WebElement> tableRow;
	List<WebElement> tableColumn;
	List<Integer> colnum;
	WebDriver driver;
	String cellData = null;

	public WebTable(WebDriver driver) {
		this.driver = driver;
		utility = new Utility(driver);
	}

	/**
	 * This method retun the row number in the table
	 * 
	 * @param tableLocator
	 * @return
	 */
	public int getTableRowNum(String tableLocator) {

		tableRow = utility.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		return tableRow.size();
	}

	/**
	 * This method return the column number of the specific row.
	 * 
	 * @param tableLocator
	 * @param rowNumber
	 * @return
	 */
	public int getTableColumnNum(String tableLocator, int rowNumber) {
		tableColumn = utility.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))
				.get(rowNumber).findElements(By.tagName("td"));
		return tableColumn.size();

	}

	/**
	 * This method return the cell value for the given row and column
	 * 
	 * @param tableLocator
	 * @param rowNumber
	 * @param columnnumber
	 * @return
	 */
	public String getCellDataByColumnAndRowNumber(String tableLocator, int rowNumber, int columnnumber) {

		for (int index = 0; index < getTableRowNum(tableLocator); index++) {
			cellData = utility.getElement(tableLocator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"))
					.get(rowNumber - 1).findElements(By.tagName("td")).get(columnnumber - 1).getText();
		}
		return cellData;
	}

	/**
	 * This method return the row and column position of given input data
	 * @param tableLocator
	 * @param cellData
	 * @return
	 */
	public String getPositionOfData(String tableLocator, String cellData) {

		int rowCount = getTableRowNum(tableLocator);
		for (int rowindex = 0; rowindex < rowCount; rowindex++) {
			int columnCount = getTableColumnNum(tableLocator, rowindex);
			for (int colIndex = 0; colIndex < columnCount; colIndex++) {
				String ActualcellData = utility.getElement(tableLocator).findElement(By.tagName("tbody"))
						.findElements(By.tagName("tr")).get(rowindex).findElements(By.tagName("td")).get(colIndex)
						.getText();
				if (cellData.equals(ActualcellData)) {
					return "The position of " + cellData + " is row=" + (rowindex + 1) + "column= " + (colIndex + 1);
				}
			}

		}
		return "No data found";
	}

	/**
	 * This method return all the data of the table body
	 * 
	 * @param tableLocator
	 */
	public void getTableData(String tableLocator) {

		int rowCount = getTableRowNum(tableLocator);
		for (int rowindex = 0; rowindex < rowCount; rowindex++) {
			int columnCount = getTableColumnNum(tableLocator, rowindex);
			for (int colIndex = 0; colIndex < columnCount; colIndex++) {
				String ActualcellData = utility.getElement(tableLocator).findElement(By.tagName("tbody"))
						.findElements(By.tagName("tr")).get(rowindex).findElements(By.tagName("td")).get(colIndex)
						.getText();
				System.out.print(ActualcellData + " ");
			}
			System.out.println();
		}

	}

}
