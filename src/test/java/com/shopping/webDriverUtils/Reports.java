package com.shopping.webDriverUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*
 * Class file with Extent Report implementation
 */

public class Reports{

	private static ExtentSparkReporter reporter;
	private static ExtentReports extent;
	public static ExtentTest logger = null;

	//Method to initialize and configure Extent Reports
	public void initializeReport() {
		reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReports/JupitorExecutionReport_"+System.currentTimeMillis()+".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Application Name", "Jupiter Toys");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Author", "Jegan N");
		reporter.config().setDocumentTitle("Online Shopping Report");
		reporter.config().setReportName("Jupiter Toys Execution Summary Report");
		reporter.config().setTheme(Theme.STANDARD);
	}

	//Method to set description for a method
	public void setTestCaseDescription(String desc) {
		logger = extent.createTest(desc);
	}

	//Method to end Extent Report
	public void closeReport() {
		extent.flush();
	}

}