package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LaunchPage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homepage;
	ContactsPage contactpage;
	LaunchPage launchpage;
	
	//Constructor
	public HomePageTest() {
		//call super class constructor:
		//call TestBase constructor, so all properties will be defined here
		super();
	}
	
	//TEST CASES SHOULD BE SEPRAT -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();// call this method from TestBase class
		//loginPage = new LoginPage();//create object of LoginPage class
		contactpage = new ContactsPage();
		launchpage = new LaunchPage();
		loginPage = launchpage.goToLoginPage();
		homepage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));// to reach home page I must login first
	}
	
	
	@Test(priority=1)
	public void validateHomePageTitleTest() {
		String homePageTitle = homepage.validateHomePageTitle();// use HomePage object to call validateHomePageTitle method
		Assert.assertEquals(homePageTitle, "Cogmento CRM", "Home page title not matched");
		
	}
		
	@Test(priority=2)
	public void verifyCorrectUserNameTest() {
		Assert.assertTrue(homepage.verifyCorrectUserName());// use HomePage object to call verifyCorrectUserName method

	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		contactpage = homepage.clickOnContactsLink();
		
	}
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
