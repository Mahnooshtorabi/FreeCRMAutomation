package com.crm.qa.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG {

	//when the method is static we can use this method without creating object of this class
	//so in other classes we can use the name of this class dot the name of method (ExtentReporterNG.getReportObject())
	public static ExtentReports getReportObject()
	{
		
		String path = System.getProperty("user.dir")+ "//reports//index.html";// in which path you want to save the report
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Naveen Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Mahnoosh Torabi");
		//extent.createTest(path); // it is not possible to write this line for each test case
		return extent; // return this object then we can use this object in the listener class
		
	}
}
