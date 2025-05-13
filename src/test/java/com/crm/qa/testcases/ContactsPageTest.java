package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LaunchPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	LoginPage loginPage;
	ContactsPage contactpage;
	HomePage homepage;
	LaunchPage launchpage;

	String sheetName = "Contacts";
	//Constructor
	public ContactsPageTest() {
		//call super class constructor:
		//call TestBase constructor, so all properties will be defined here
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();// call this method from TestBase class
		launchpage = new LaunchPage();
		loginPage = launchpage.goToLoginPage();
		homepage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));// to reach home page I must login first
		contactpage = homepage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest() {
		Assert.assertTrue(contactpage.verifyContactsPageLabel(), "contacts label is missing on the page");
		
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest() {
		
		contactpage.selectContacts("test2 test2");
	}
	
	
	@Test(priority=3)
	public void selectMultipleContactsTest() throws InterruptedException {
		
		contactpage.selectContacts("test2 test2");
		contactpage.selectContacts("Naveen Naveen");
		Thread.sleep(5000);
	}
	
	
	@DataProvider
	public Object[][] getCRMTestData () {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String firstName, String lastName, String company){
		contactpage.clickOnCreateNewContactLink();
		//contactpage.createNewContactForm("Saina", "Basiri", "Freedom");
		contactpage.createNewContactForm(firstName, lastName, company);
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
