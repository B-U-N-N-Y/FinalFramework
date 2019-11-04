package com.atmecs.FinalFramework.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.atmecs.FinalFramework.constant.FileConstant;
import com.atmecs.FinalFramework.dataProvider.TestDataProvider;
import com.atmecs.FinalFramework.utils.ReadPropertiesFile;

public class DynamicXML {
	@Test(dataProvider = "dynamic_class", dataProviderClass = TestDataProvider.class)
	public void runTestFile(String classname, String statusofclass) throws ClassNotFoundException {
		Properties props = new ReadPropertiesFile().loadProperty(FileConstant.CONFIG_FILE);

		List<String> browsernames = new ArrayList<String>();
		String[] array = props.getProperty("browser").split(",");
		for (String browser : array) {
			browsernames.add(browser);
		}

		if (statusofclass.equalsIgnoreCase("Y")) {
			browsernames.forEach(browser -> System.out.println(browser));
			XmlSuite xmlSuite = new XmlSuite();
			xmlSuite.setName("suite");
			xmlSuite.setVerbose(1);
			xmlSuite.setParallel(ParallelMode.TESTS);
			xmlSuite.setThreadCount(browsernames.size());

			List<XmlSuite> suites = new ArrayList<XmlSuite>();

			for (String browser : browsernames) {

				XmlTest xmlTest1 = new XmlTest(xmlSuite);
				Map<String, String> parameter1 = new HashMap<String, String>();
				parameter1.put("browser", browser);
				xmlTest1.setParameters(parameter1);
				xmlTest1.setName("Test validate in " + browser);

				// code to read your testNg file

				XmlClass Class = new XmlClass();
				Class.setName(classname);

				List<XmlClass> xmlClassList = new ArrayList<XmlClass>();
				xmlClassList.add(Class);

				xmlTest1.setXmlClasses(xmlClassList);

			}

			suites.add(xmlSuite);

			TestNG testng = new TestNG();

			testng.setXmlSuites(suites);

			testng.run();
		}

	}
}
