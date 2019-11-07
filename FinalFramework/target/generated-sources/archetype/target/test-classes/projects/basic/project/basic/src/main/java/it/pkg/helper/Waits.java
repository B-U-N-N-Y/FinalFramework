package it.pkg.helper;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import it.pkg.constant.FileConstant;

/**
 * This class contains all type of wait method.
 * 
 * @author arjun.santra
 *
 */
public class Waits {

	public By selectLocator(String locator) {
		By by = null;
		String[] loc = locator.split(",", 2);

		switch (loc[0].toUpperCase()) {
		case "ID":
			by = (By.id(loc[1]));
			break;
		case "NAME":
			by = (By.name(loc[1]));
			break;
		case "CLASS":
			by = (By.className(loc[1]));
			break;
		case "LINKTEXT":
			by = (By.linkText(loc[1]));
			break;
		case "PARTIAL":
			by = (By.partialLinkText(loc[1]));
			break;
		case "CSS":
			by = (By.cssSelector(loc[1]));
			break;
		case "TAGNAME":
			by = (By.tagName(loc[1]));
			break;
		case "XPATH":
			by = (By.xpath(loc[1]));
			break;
		}
		return by;

	}

	/**
	 * This method check that web element is to be clickable or not otherwise wait
	 * for the given time
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public WebElement waitElementToBeClickable(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, FileConstant.TIMEOUT_INSECONDS);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(selectLocator(locator)));
		return element;

	}

	/**
	 * This method check that web element is to be clickable or not otherwise wait
	 * for the given time
	 * 
	 * @param driver
	 * @param webElement
	 * @return
	 */
	public WebElement waitElementToBeClickable(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, FileConstant.TIMEOUT_INSECONDS);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
		return element;

	}

	/**
	 * This method check that web element is visible or not otherwise wait until the
	 * given time
	 * 
	 * @param driver
	 * @param webElement
	 * @return
	 */
	public WebElement waitVisibilityOf(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, FileConstant.TIMEOUT_INSECONDS);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
		return element;
	}

	/**
	 * This method check that web element is visible or not otherwise wait until the
	 * given time
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 */
	public WebElement waitPresenceOfElementLocated(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, FileConstant.TIMEOUT_INSECONDS);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(selectLocator(locator)));
		return element;
	}

	/**
	 * This method provide fluent wait to driver
	 * 
	 * 
	 * @param driver
	 * @return
	 */
	public FluentWait<WebDriver> fluientWait(WebDriver driver) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).ignoring(Exception.class)
				.pollingEvery(Duration.ofSeconds(FileConstant.POLLING_TIMEOUT_INSECONDS))
				.withTimeout(Duration.ofSeconds(FileConstant.TIMEOUT_INSECONDS));
		return wait;
	}

	/**
	 * This method stop the working of web driver for the given time
	 * 
	 * @param waitingTimeInMiliSeconds
	 */
	public void hardWait(long waitingTimeInMiliSeconds) {
		try {
			Thread.sleep(waitingTimeInMiliSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean waitPresenceOfElementLocated(WebDriver driver, WebElement element, long waitingTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitingTime);
		boolean status = wait.until(ExpectedConditions.stalenessOf(element));
		return status;
	}

}
