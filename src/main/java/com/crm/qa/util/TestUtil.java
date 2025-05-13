package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Sheet; 
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;


import com.crm.qa.base.TestBase;


public class TestUtil extends TestBase{

	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	
	//sheetpath
	public static String TESTDATA_SHEET_PATH ="C:/Users/andal/eclipse-workspace/FreeCRMTest/src/main/java/com/crm/qa/testdata/FreeCrmTestDataNaveen.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	
	//switch to frame is a common method and applicable to all pages
	//it is not belonging to a specific page
	//so we should write this method in TestUtil.java
	public void switchtoFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	
	
	//getTestData method for @DataProvider we need it
	public static Object[][] getTestData(String sheetName) {
	    FileInputStream file = null;

	    try {
	        // Load the Excel file from the given path
	        file = new FileInputStream(TESTDATA_SHEET_PATH);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }

	    try {
	        // Create a Workbook instance from the file
	        book = WorkbookFactory.create(file);
	    } catch (InvalidFormatException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Get the specific sheet by name
	    sheet =  book.getSheet(sheetName);

	    // Get number of rows (excluding the header row)
	    int totalRows = sheet.getLastRowNum();

	    // Get number of columns from the first row (header)
	    int totalCols = sheet.getRow(0).getLastCellNum();

	    // Create a 2D array to store the data
	    Object[][] data = new Object[totalRows][totalCols];

	    // Read data from Excel and store in the array
	    for (int i = 0; i < totalRows; i++) {
	        for (int k = 0; k < totalCols; k++) {
	            // Start from row index i+1 to skip the header row
	            data[i][k] =  sheet.getRow(i + 1).getCell(k).toString();
	        }
	    }

	    return data;
	}

	
	
	
	//Take screen shot Method
	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") +"//reports//"+ testCaseName + ".png");//path of screenshot
		FileUtils.copyFile(scrFile, file);
		return System.getProperty("user.dir") +"//reports//"+ testCaseName + ".png";
		// this method at the end give us the path of the screenshot where it stored in your local system
	}


		
	}
	
