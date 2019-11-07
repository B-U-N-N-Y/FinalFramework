package it.pkg.reports;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

import it.pkg.constant.FileConstant;



/**
 * 
 * @author arjun.santra This class give the log report of testscripts
 *
 */
public class LogReport {

	Logger logger = null;

	public LogReport() {
		getlogger();
		logger = Logger.getLogger(LogReport.class.getName());
	}

	public void getlogger() {
		PropertyConfigurator.configure(FileConstant.LOG4J_FILE);
	}

	/**
	 * the method takes input as string message
	 * 
	 * @param message is printed on the console
	 */
	public void info(String message) {
		logger.info(message);
		Reporter.log(message);
	}

}
