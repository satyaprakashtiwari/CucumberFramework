package stepDefinitions;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCustomer;
	
	public static Logger logger;
	public Properties pro;
	
	public static String getRandomData_Email() {
		
		return new Faker().name().firstName()+"@yahoo.com";
	}

}
