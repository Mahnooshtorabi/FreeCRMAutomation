package com.crm.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.qa.ExtentReport.ExtentReporterNG;
import com.crm.qa.base.TestBase;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//ITestListener interface which implements Testng listeners and ITestListener provided by TestNg
public class WebEventListener extends TestBase implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject(); // create object of ExtentReport
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();// Thread safe -- in parallel execution each test case has its own thread and test variable will not overridden
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//before any test this block will be executed
		//the below line will create an entry for this test case in the report
		test = extent.createTest(result.getMethod().getMethodName()); // here we must get testCaseName
		extentTest.set(test);//assign unique thread id -- every instance of running has its own thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//only when test is successful this block will be executed
		//test.log(Status.PASS, "Test Passed"); //only it will log that this test case is passed and actually it is not important because test is passed and every thing is good
		extentTest.get().log(Status.PASS, "Test Passed"); //use this line instead of above line to prevent parallel run issue
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//test.fail(result.getThrowable()); // this will return error message 
		//instead of test in above line we should use extentTest to prevent parallel run issue
		extentTest.get().fail(result.getThrowable());// this will return error message 
		
		//get driver to get driver life to use it in screenshot code
		//we cannot use getMethod() and we must use getTestClass() because Field is related to class level not method level
		try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//screenshot, and attach it to the report
		String filePath= null;
		try {
			filePath =TestUtil.getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());//(path of screen shot, related to which testCaseName?)
		
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		//generate report when all testcases were run
		extent.flush();
	}

}


