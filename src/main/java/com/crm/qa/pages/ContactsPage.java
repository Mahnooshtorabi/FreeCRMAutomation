package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{

	
		//1- Xpath page factory
	
		//Contacts page label
		@FindBy(xpath = "//div[@class='ui header item mb5 light-black']")
		WebElement ContactsPageLabel;
		
		//Header Logo
		@FindBy(xpath = "//div[@class='header item']")
		WebElement headerLogo;
		
		//Create New Contact Button
		@FindBy(xpath = "//button[text()='Create']")
		WebElement createNewContactBtn;
		
		//firstNameTextbox
		@FindBy(xpath = "//input[@name='first_name']")
		WebElement firstName;
		
		@FindBy(xpath = "//input[@name='last_name']")
		WebElement lastName;
		
		@FindBy(xpath = "//div[@name='company']//input")
		WebElement companyName;
		
		@FindBy(xpath = "//button[text() ='Save']")
		WebElement SaveBtn;
		
		
				
		
		/*
		//this is not good to use this chon momkene dafe bad bekhay jaye test2 ye chiz dige ro tik bezani
		//bejash to method selectContacts minevisim va inja az in ravash neminevisam 
		@FindBy(xpath = "//a[contains(text(),'test2 test2')]/parent::td//preceding-sibling::td")
		WebElement Checkboxes;
		*/
		
		
	
		//define constructor
		public ContactsPage() {
			
			// initialize page factory (Page Objects) with initElements method
			PageFactory.initElements(driver, this);//this: means current class object
		}
		
		
		//Actions		
		//verify contacts page label at the top of the Contacts Page
		public boolean verifyContactsPageLabel() {
				Actions action = new Actions(driver);
				action.moveToElement(headerLogo).build().perform();// In case hover is needed
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			    wait.until(ExpectedConditions.visibilityOf(ContactsPageLabel));
			    System.out.println(ContactsPageLabel.getText());
				return ContactsPageLabel.isDisplayed();
		}
		

		
		//click on one of contact's check boxes
		public void selectContacts(String name) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
		        By.xpath("//a[contains(text(),'"+name+"')]/parent::td//preceding-sibling::td")
		    ));

		    // Scroll into view
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
		    wait.until(ExpectedConditions.elementToBeClickable(checkbox));
		    
		    // Click using JavaScript to bypass overlay
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
		}

		//click on create button
		public void clickOnCreateNewContactLink() {
			createNewContactBtn.click();
			
		}
		
		//fill the new contact form
		public void createNewContactForm(String ftName, String ltName, String compName){
			firstName.sendKeys(ftName);
			//Thread.sleep(2000);
			lastName.sendKeys(ltName);
			//Thread.sleep(2000);
			companyName.sendKeys(compName);
			//Thread.sleep(2000);
			companyName.sendKeys(Keys.ENTER);
			SaveBtn.click();
		
		}
		
		
		
}
