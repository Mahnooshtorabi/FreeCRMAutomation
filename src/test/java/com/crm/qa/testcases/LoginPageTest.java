package com.crm.qa.testcases;

import org.testng.annotations.Test; 
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LaunchPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Retry;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homepage;
	LaunchPage launchpage;
	
	//Constructor
	public LoginPageTest() {
		//call super class constructor:
		//call TestBase constructor, so all properties will be defined here
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {
		initialization();// call this method from TestBase class
		//loginPage = new LoginPage();//create object of LoginPage class
		launchpage = new LaunchPage();
		loginPage = launchpage.goToLoginPage();
	}
	
	@Test(priority=1)
	public void LoginPageTitleTest() {
		String loginPageTitle = loginPage.validateLoginPageTitle();// use loginPage object to call validateLoginPageTitle method
		Assert.assertEquals(loginPageTitle, "Cogmento CRM");
	
	}
	
	@Test(priority=2, retryAnalyzer = Retry.class) //if this test fail it will rerun
	public void LoginTest() {
		// use loginPage object to call login method
		homepage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));

	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
		
}

