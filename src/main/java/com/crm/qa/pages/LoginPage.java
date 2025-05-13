package com.crm.qa.pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	//we will write object and method of login page in this class
	
	//1-define Page Factory of LoginPage (Object Repository)
	//@FindBy(name = "email_22") //if you want to have fail you can use this line
	@FindBy(name = "email")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//div[text()='Login']")
	WebElement loginBtn;
		
	@FindBy(xpath = "//a[text()='Sign Up']")
	WebElement SignUpBtn;
	
	
	//2-define constructor
	public LoginPage() {
		
		// initialize page factory (Page Objects) with initElements method
		PageFactory.initElements(driver, this);//this: means current class object
	}
	
	
	//3-Actions
	//define different actions (different features in login page)
	public String validateLoginPageTitle() {
		
		return driver.getTitle();
		
	}
		
	
	public HomePage login (String un, String pwd) {
	
		/*Set<String> windows = driver.getWindowHandles();//[parentID, ChildID]
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);*/
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(username));
	 
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		// after clicking on LoginBtn it moves to Homepage,
		//so it should return HomePage class objest
		return new HomePage();
	}
	
	
}
