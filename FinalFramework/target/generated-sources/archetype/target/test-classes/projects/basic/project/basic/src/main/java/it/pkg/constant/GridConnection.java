package it.pkg.constant;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * This class operate Grid Connection
 * @author arjun.santra
 *
 */
public class GridConnection {
	public  URL url;
	static String nodeurl;
	
	
	
	/**
	 * This method create the remote  grid connection and return the driver
	 * @param driver
	 * @param browser
	 * @param huburl
	 * @return
	 */
	public  WebDriver gridCon(WebDriver driver,String browser,String huburl) {

		nodeurl =huburl+"/wd/hub";

		DesiredCapabilities capability = new DesiredCapabilities();
		
		try {
			url = new URL(nodeurl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(browser.equals("chrome")) {
			capability=DesiredCapabilities.chrome();
			capability.setBrowserName(browser);
			
		}
		else if(browser.equals("firefox")) {
		 capability=DesiredCapabilities.firefox();
		capability.setBrowserName(browser);
		
		}
		else if(browser.equals("internet explorer")) {
			 capability=DesiredCapabilities.internetExplorer();
			capability.setBrowserName(browser);
			
			}
		capability.setPlatform(Platform.WINDOWS);
		driver = new RemoteWebDriver(url, capability);
		return driver;
	}
}
