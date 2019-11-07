package com.atmecs.FinalFramework.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * This class to handle the web element by using java script executor.
 * @author arjun.santra
 *
 */
public class JavaScriptExecuter {
	WebDriver driver;

	public JavaScriptExecuter(WebDriver driver) {
		this.driver = driver;
	}

	Waits waitObject = new Waits();

	/**
	 * the method is used for clicking the element using fluent wait
	 * 
	 * @param locator
	 */
	public void clickElement(String locator) {
		try {
			WebElement element = waitObject.waitElementToBeClickable(driver, locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception exception) {
			System.out.println("Element not clickable");
			exception.printStackTrace();
		}
	}

	/**
	 * the method is used for getting the url of web page
	 * 
	 * @param locator
	 */
	public String getUrl() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String url = js.executeScript("return document.URL;").toString();
		return url;
	}

	/**
	 * the method is used for getting the title of web page
	 * 
	 * @param locator
	 */
	public String getTitle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String url = js.executeScript("return document.title;").toString();
		return url;
	}

	/**
	 * the method is used for sending the text using flent wait
	 * 
	 * @param locator
	 * @param text
	 */
	public void sendText(String locator, String text) {
		try {
			WebElement element = waitObject.waitElementToBeClickable(driver, locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='[xxx]';".replace("[xxx]", text), element);
		} catch (Exception exception) {
			System.out.println("Element not sending text");
			exception.printStackTrace();
		}
	}

	/**
	 * the method is used for sending the text and pressing the enter using fluent
	 * wait
	 * 
	 * @param locator
	 * @param text
	 */
	public void sendTextAndEnter(String locator, String text) {
		try {
			WebElement element = waitObject.waitElementToBeClickable(driver, locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='[xxx]';".replace("[xxx]", text), element);
			element.sendKeys(Keys.TAB);
		} catch (Exception exception) {
			System.out.println("Element not sending text");
			exception.printStackTrace();
		}
	}

	/**
	 * the method is used for getting the text using fluent wait
	 * 
	 * @param locator
	 */
	public String getText(String locator) {
		try {
			WebElement element = waitObject.waitElementToBeClickable(driver, locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String text = (String) (executor.executeScript("return arguments[0].innerHTML;", element));
			return text;
		} catch (Exception exception) {
			System.out.println("Element not getting text");
			exception.printStackTrace();
		}
		return null;
	}
}
