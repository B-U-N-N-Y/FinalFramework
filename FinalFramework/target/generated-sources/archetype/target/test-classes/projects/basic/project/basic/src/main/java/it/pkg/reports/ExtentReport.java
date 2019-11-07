package it.pkg.reports;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import it.pkg.constant.FileConstant;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
/**
 * This class create the extent report for the test script
 * @author arjun.santra
 *
 */
public class ExtentReport implements IReporter {
	public ExtentReports extent;
	public ExtentTest test;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputdirectory) {
		extent = new ExtentReports(FileConstant.USER_HOME + File.separator + "Extent.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	public void buildTestNodes(IResultMap tests, LogStatus status) {

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				String message = "Test " + status.toString().toLowerCase() + "ed";
				if (result.getThrowable() != null)
					message = result.getThrowable().getClass() + ": " + result.getThrowable().getMessage();
				test.log(status, message);
				for (String testMessage : Reporter.getOutput(result)) {
					test.log(LogStatus.INFO, testMessage);
				}
				extent.endTest(test);
			}
		}
	}

	public Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
