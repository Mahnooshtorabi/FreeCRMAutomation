package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

//we call this class page library
public class HomePage extends TestBase{

	//Mahnoosh Torabi at the top of the page
	@FindBy(xpath = "//span[@class='user-display']")
	@CacheLookup //it will store userNameLabel in a memory, so instead of get that from page it will get that from memory(make the spped of framework higher)
	WebElement userNameLabel;
	
	//Contacts button
	@FindBy(xpath = "//i[@class='users icon']")
	WebElement contactsLink;
	
	//Deals button
	@FindBy(xpath = "//i[@class='money icon']")
	WebElement dealsLink;
	
	//Tasks button
	@FindBy(xpath = "//i[@class='tasks icon']")
	WebElement taskLink;
	
	
	
	//define constructor
	public HomePage() {
		
		// initialize page factory (Page Objects) with initElements method
		PageFactory.initElements(driver, this);//this: means current class object
	}
	
	
	//Actions
	//define different actions (different features in login page)
	public String validateHomePageTitle() {
		
		return driver.getTitle();
		
	}
	
	//verify name of user at the top of the homepage
	public boolean verifyCorrectUserName() {
		 return userNameLabel.isDisplayed();
	}
	
		
	public ContactsPage clickOnContactsLink() {
		
		contactsLink.click();
		return new ContactsPage();//next landing page obj
	}
	
	public DealsPage clickOnDealsLink() {
		
		dealsLink.click();
		return new DealsPage();//next landing page obj
	}
	
	public TasksPage clickOnTasksLink() {
		
		taskLink.click();
		return new TasksPage();//next landing page obj
	}
	
	
	
}
