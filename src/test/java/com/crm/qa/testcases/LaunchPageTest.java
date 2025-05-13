package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LaunchPage;
import com.crm.qa.pages.LoginPage;

public class LaunchPageTest extends TestBase {
	LaunchPage launchpage;
	LoginPage loginpage;
	
	
	 //Constructor
		public LaunchPageTest() {
			//call super class constructor:
			//call TestBase constructor, so all properties will be defined here
			super();
		}
		
		@BeforeMethod
		public void setUp() {
			initialization();// call this method from TestBase class
			launchpage = new LaunchPage();//create object of LaunchPage class
			
		}
		
		@Test(priority=1)
		public void LaunchPageTitleTest() {
			String title = launchpage.validateLaunchPageTitle();// use launchpage object to call validateLaunchPageTitle method
			Assert.assertEquals(title, "#1 Free CRM Software Power Up your Entire Business Free Forever");

		}
		
		@Test(priority=2)
		public void LaunchPageFacebookLogoTest() {
			boolean title = launchpage.validateFacebookLogo();// use launchpage object to call validateFacebookLogo method
			Assert.assertTrue(true);
		
		}
		
		@Test(priority=3)
		public void LaunchPageStartHereTest() {
			loginpage = launchpage.goToLoginPage();
		
		}
		
		
		
		@AfterMethod
		public void tearDown() {
			driver.quit();
		}
		
}
