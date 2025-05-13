package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	//Initialize all properties for example:
	//launch Chrome//Maximize window//loadpageTimeOut//DeleteAllCookies
	
	public static WebDriver driver;
	public static Properties prop;
	
//	public static WebEventListener eventListener;
	
	
	//create constructor and read properties file
	public TestBase() {
		try
		{
			prop=new Properties();
			// Address file config.properties ro to line zir minevisim
			FileInputStream ip = new FileInputStream("C:\\Users\\andal\\eclipse-workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public static void initialization() {
		//if you write in terminal from Maven: (mvn test -Dbrowser=FF) --> FF is not equal to nulls? yes it is true
		// so, we are using second argument : System.getProperty("browser")--> go and get value from maven
		//if you did nor write in your terminal about browser and only for example is like (mvn test -POnlytest) -->FF is not equal to nulls? No  it is false
		//so, we are using third argument : prop.getProperty("browser") --> go and get value from config.properties
			String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
			if(browserName.contains("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:/Users/andal/Downloads/chromedriver-win64 (2)/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				if(browserName.contains("headless")){
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);//launch chrome
						
			
			}else if (browserName.equals("Firefox")) {
				    System.setProperty("webdriver.gecko.driver", "C:/Users/andal/Downloads/geckodriver-v0.36.0-win32/geckodriver.exe");

				    // Set Firefox binary path manually
				    FirefoxOptions options = new FirefoxOptions();
				    options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe"); // Make sure this path matches your actual Firefox install

				    driver = new FirefoxDriver(options); // Launch Firefox with options
				}
			
			// Create an instance of your event listener
		  //  eventListener = new WebEventListener();

		    // Wrap the original driver with EventFiringDecorator
		   // driver = new EventFiringDecorator<>(eventListener).decorate(driver);
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
			
			driver.get(prop.getProperty("url"));
		}
	
	}
	
	
	

