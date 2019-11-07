package it.pkg.helper;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import it.pkg.constant.FileConstant;

/**
 * This class contains all the methods which are required to do automation for a
 * web application
 * 
 * @author arjun.santra
 *
 */
public class Utility {
	WebDriver driver;

	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement element = null;

	public void selectLocator(String locator) {

		String[] loc = locator.split(",", 2);

		switch (loc[0].toUpperCase()) {
		case "ID":
			element = driver.findElement(By.id(loc[1]));
			break;
		case "NAME":
			element = driver.findElement(By.name(loc[1]));
			break;
		case "CLASS":
			element = driver.findElement(By.className(loc[1]));
			break;
		case "LINKTEXT":
			element = driver.findElement(By.linkText(loc[1]));
			break;
		case "PARTIAL":
			element = driver.findElement(By.partialLinkText(loc[1]));
			break;
		case "CSS":
			element = driver.findElement(By.cssSelector(loc[1]));
			break;
		case "TAGNAME":
			element = driver.findElement(By.tagName(loc[1]));
			break;
		case "XPATH":
			element = driver.findElement(By.xpath(loc[1]));
			break;
		}

	}

	/**
	 * This method use to click on web element
	 * 
	 * @param locator
	 */
	public void clickElement(final String locator) {
		try {

			selectLocator(locator);

			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.ignoring(ElementClickInterceptedException.class)
					.pollingEvery(Duration.ofSeconds(FileConstant.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(FileConstant.TIMEOUT_INSECONDS));
			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					selectLocator(locator);
					element.click();
					return true;
				}
			});
		} catch (Exception exception) {
			System.out.println("Element is not clickable or not present");
			exception.printStackTrace();
		}
	}

	/**
	 * This method select any option from the drop down in a web application
	 * 
	 * @param locator
	 * @param text
	 */
	public void selectDropdownByText(final String locator, final String text) {
		try {
			selectLocator(locator);
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.pollingEvery(Duration.ofSeconds(FileConstant.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(FileConstant.TIMEOUT_INSECONDS));
			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					Select trip = new Select(element);
					trip.selectByVisibleText(text);
					return true;
				}

			});
		} catch (Exception exception) {
			System.out.println("Drop down functionality is not working");
			exception.printStackTrace();
		}
	}

	/**
	 * method click the blank fields and send the text to be entered
	 * 
	 * @param locator
	 * @param text
	 */
	public void clickAndSendText(final String locator, final String text) {
		try {
			selectLocator(locator);
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.ignoring(ElementClickInterceptedException.class)
					.pollingEvery(Duration.ofSeconds(FileConstant.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(FileConstant.TIMEOUT_INSECONDS));

			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					element.sendKeys(text);
					;
					return true;
				}
			});
		} catch (Exception exception) {
			System.out.println("Send text is not working");
		}
	}

	/**
	 * 
	 * @param locator
	 * @return web element
	 */
	public WebElement getElement(String locator) {
		try {
			selectLocator(locator);
			return element;
		} catch (Exception exception) {
			System.out.println("Element is not present");
		}
		return element;
	}

	/**
	 * method check that specific web element is displayed in the web application or
	 * not
	 * 
	 * 
	 * @param locator
	 * @return boolean value
	 */
	public boolean isDisplayed(String locator) {
		selectLocator(locator);
		boolean isDisplayed = false;
		try {
			isDisplayed = element.isDisplayed();
		} catch (Exception e) {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	/**
	 * method check that web element is selected or not
	 * 
	 * if it is selected this method return true otherwise return false
	 * 
	 * @param locator
	 * @return boolean value
	 */
	public boolean isSelected(String locator) {
		selectLocator(locator);
		boolean isSelected = false;
		try {
			isSelected = element.isSelected();
		} catch (Exception e) {
			isSelected = false;
		}
		return isSelected;
	}

	/**
	 * method scroll down the window on the web page
	 * 
	 * 
	 * @param horizontal index
	 * @param vertical   index
	 */

	public void scrollDownPage(int horizontalIndex, int verticalIndex) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String scroll = "window.scrollBy(" + horizontalIndex + "," + verticalIndex + ")";
		js.executeScript(scroll);
	}

	/**
	 * In this method,Input boxes entered texts are cleared
	 * 
	 * 
	 * @param locator
	 */
	public void clearField(String locator) {
		try {
			selectLocator(locator);
			element.clear();
		} catch (Exception exception) {
			System.out.println("Clear field functionality is not working");
		}
	}

	/**
	 * Method gets the title of the current page
	 */
	public String getTitle() {
		String title = null;
		try {
			title = driver.getTitle();

		} catch (Exception exception) {
			System.out.println("Title is not getting");
		}
		return title;
	}

	/**
	 * Method scrolls down the window resolution until the view of webelement is not
	 * found
	 * 
	 * 
	 * @param locator
	 */
	public void scrollIntoview(String locator) {
		try {
			selectLocator(locator);
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception exception) {
			System.out.println("scroll into view in not working");
		}
	}

	/**
	 * In this method, mouse over operation of the mouse is done
	 * 
	 * @param locator
	 */
//	public void actionMoveToElement(String locator) {
//		try {
//			Thread.sleep(2000);
//			Actions action = new Actions(driver);
//			selectLocator(locator);
//			if (browser.equals("firefox"))
//				action.click(element).build().perform();
//			else
//				action.moveToElement(element).build().perform();
//		} catch (Exception exception) {
//			System.out.println("System interrupted");
//		}
//	}

	/**
	 * method select the dropdown and select the Element by index, using the fluent
	 * wait concepts ignoring the ElementClickInterceptedException.
	 * 
	 * @param locator
	 * @param index
	 */
	public void selectDropdownByIndex(final String locator, final int index) {
		try {
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.pollingEvery(Duration.ofSeconds(FileConstant.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(FileConstant.TIMEOUT_INSECONDS));
			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					selectLocator(locator);
					Select trip = new Select(element);

					trip.selectByIndex(index);
					return true;
				}

			});

		} catch (Exception exception) {
			System.out.println("Drop down by index not working");
		}
	}

	/**
	 * Method sets the attribute value of the web element
	 * 
	 * @param locator
	 * @param xOffset
	 */
	public void setAttributeValue(String locator, int xOffset) {
		try {
			selectLocator(locator);
			Actions move = new Actions(driver);
			move.clickAndHold(element).dragAndDropBy(element, xOffset, 0).build().perform();

		} catch (Exception exception) {

			System.out.println("slider bar is not working");
		}
	}

	/**
	 * 
	 * This method take a input as a string and return a list of web elements
	 * 
	 * @param locator
	 * @return the list of elements
	 */

	public List<WebElement> getElementsList(final String locator) {
		String[] loc = locator.split(",");
		List<WebElement> element;
		if ((loc[0].toUpperCase()).equalsIgnoreCase("CSS")) {
			element = driver.findElements(By.cssSelector(loc[1]));
		} else {
			element = driver.findElements(By.xpath(loc[1]));
		}

		return element;
	}

	/**
	 * This method use to click a element multiple times
	 * 
	 * @param num
	 * @param locator
	 */
	public void clickElementMultipleTime(int num, String locator) {
		selectLocator(locator);
		for (int index = 0; index < num; index++) {
			element.click();
		}

	}
}
