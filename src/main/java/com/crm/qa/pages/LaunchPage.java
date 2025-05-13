package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LaunchPage extends TestBase{

	//1-Page Factory of LoginPage (Object Repository)
	@FindBy(xpath = "//div[@class='MuiBox-root mui-19wnbjs']//a[text()='Sign Up']")
	WebElement SignUpBtn1;
	
	//start here button
	@FindBy(xpath ="//a[@href='https://ui.cogmento.com/register/']")
	WebElement StartHere;
	
	@FindBy(xpath ="//div[@class='MuiBox-root mui-19wnbjs']//a[text()='Login']")
	WebElement loginBtn1;
	
	@FindBy(xpath ="//img[@alt='free crm facebook integrations']")
	WebElement FacebookLogo;
	
	//2-Constructor
	//initialize page factory (Page Objects) with initElements method
	public LaunchPage() {
			
		PageFactory.initElements(driver, this);//this: means current class object
	}
		
	
	//3-Actions
	//define different actions (different features in login page)
	public String validateLaunchPageTitle() {
			
		return driver.getTitle();
			
	}
	
	public boolean validateFacebookLogo() {
		
		return FacebookLogo.isDisplayed();
			
	}
	
	public LoginPage goToLoginPage() {
		
		StartHere.click();
		return new LoginPage();
			
	}
	
	public SignUpPage goToSignUpPage() {
		
		SignUpBtn1.click();
		return new SignUpPage();
			
	}
	
	
	
}
