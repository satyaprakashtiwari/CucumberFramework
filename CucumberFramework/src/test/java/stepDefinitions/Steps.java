package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.github.javafaker.Faker;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	
	@Before
	public void setup() throws IOException {
		
		pro= new Properties();
		FileInputStream fis= new FileInputStream("config.properties");
		pro.load(fis);
		
		logger= Logger.getLogger("Cucumber Framework"); //added logger
		PropertyConfigurator.configure("log4j.properties");
		
		String browser= pro.getProperty("browser");
		
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", pro.getProperty("chromepath"));
		    driver= new ChromeDriver();	
		}
		else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", pro.getProperty("firefoxpath"));
		    driver= new FirefoxDriver();	
		}
		else if(browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", pro.getProperty("iepath"));
		    driver= new InternetExplorerDriver();	
		}
		
		
	    
	    logger.info("*******Launching the browser*******");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
		
	@Given("user launch chrome browser")
	public void user_launch_chrome_browser() {
		
		
	    lp= new LoginPage(driver);
	}

	@When("user opens url {string}")
	public void user_opens_url(String url) {
	    driver.get(url);
	    logger.info("*******Opening the URL*******");
	}

	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		
		logger.info("------entering USR and PWD-------");
	    lp.setUsername(email);
	    lp.setPassword(password);
	}

	@When("click on login")
	public void click_on_login() {
		
	    lp.clickLogin();
	    logger.info("---login done-----");
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitle) throws InterruptedException {
		Thread.sleep(5000);
	    String actualTitle= driver.getTitle();
	    if(driver.getPageSource().contains("Login was unsuccessful")) {
	    	logger.info("-------titile is matching-------");
	    	driver.close();
	    	Assert.assertTrue(false);
	    }
	    else {
	    	logger.error("-----title not matching-------");
	    	Assert.assertEquals("Title is matching", expectedTitle, actualTitle);
	    }
	}

	@When("user click on logout link")
	public void user_click_on_logout_link() throws InterruptedException {
		Thread.sleep(3000);
	    lp.clickLogout();
	    logger.info("--------clicked on logout--------");
	}

	@Then("close browser")
	public void close_browser() throws InterruptedException {
		Thread.sleep(5000);
	    driver.close();
	    logger.info("*******Closing the browser*******");
	}
	
	// new steps added for adding customers
	
	@Then("user can view dashboard")
	public void user_can_view_dashboard() {
	    
		addCust= new AddCustomerPage(driver);
		logger.info("-----------verifying title------");
		Assert.assertEquals("Title is matching", "Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("user click on customers menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
	    addCust.clickOnCustomerMenu();
	    logger.info("-----------clicked on CUSTOMER----------");
	}

	@When("click on customers menu item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(3000);
	    addCust.clickOnCustomerMenuItem();
	    logger.info("-----------clicked on Customer----------");
	}

	@When("click on add new button")
	public void click_on_add_new_button() throws InterruptedException {
	    addCust.clickOnAddNew();
	    Thread.sleep(2000);
	    logger.info("-----------clicked on AddNew button----------");
	}

	@Then("user can view Add New Customer page")
	public void user_can_view_Add_New_Customer_page() {
		logger.info("-----------verifying add new customer page----------");
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	    
	}

	@When("user enter customer info")
	public void user_enter_customer_info() {
		logger.info("-----------going to enter customer details---------");
		Faker faker= new Faker();
		String fistname=faker.name().firstName();
		String lastname=faker.name().lastName();
		System.out.println("Name of customer is: "+fistname+" "+lastname);
	    addCust.setEmail(fistname+lastname+"@gmail.com");
	    addCust.setPassword("test123");
	    addCust.setFirstName(fistname);
	    addCust.setLastName(lastname);
	    addCust.setGender("Male");
	    addCust.setDateOfBirth("12/30/2019");//mm/dd/yyyy
	    addCust.setCompanyName("Apple");
	    //addCust.setCustomerRole("Vendors");
	    addCust.setManagerOfVendor("Vendor 1");
	    addCust.setAdminContent("This is content by admin");
	    logger.info("-----------customer details entered---------");
	}

	@When("click on save button")
	public void click_on_save_button() throws InterruptedException {
	    addCust.clickOnSaveButton();
	    logger.info("-----------clicked on save button---------");
	    Thread.sleep(3000);
	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String message) {
		logger.info("-------------verifying the confirmation message----------");
	    Assert.assertTrue(addCust.getPageBody().contains(message));
	}
	
	//adding new steps for searching customer by email
	
	@When("enter customer email")
	public void enter_customer_email() {
	    searchCustomer= new SearchCustomerPage(driver);
	    searchCustomer.setEmail("victoria_victoria@nopCommerce.com");
	    logger.info("--------------entered customer email-------------");
	}

	@When("click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCustomer.clickSearch();
		Thread.sleep(3000);
		logger.info("--------------clicked on search-------------");
	}

	@Then("user should found email in search table")
	public void user_should_found_email_in_search_table() {
		logger.info("--------------checking email in table-------------");
		boolean flag=searchCustomer.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertTrue(flag);
	}
	
	//for searching by name
	
	@When("enter customer firstname")
	public void enter_customer_firstname() {
		searchCustomer= new SearchCustomerPage(driver);
		searchCustomer.setFirstName("Victoria");
		logger.info("--------------entered first name-------------");
	}

	@When("enter customer lastname")
	public void enter_customer_lastname() {
		searchCustomer.setLastName("Terces");
		logger.info("--------------entered last name-------------");
	}
	
	@Then("user should found name in search table")
	public void user_should_found_name_in_search_table() {
		logger.info("--------------checking user in table-------------");
		boolean flag=searchCustomer.searchCustomerByName("Victoria Terces");
		Assert.assertTrue(flag);
	}

}
