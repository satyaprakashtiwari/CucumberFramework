package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	public WebDriver driver;
	
	public AddCustomerPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='#']//span[text()='Customers']")
	WebElement lnkCustomers_menu;
	
	public void clickOnCustomerMenu() {
		lnkCustomers_menu.click();
	}
	
	@FindBy(xpath="//span[@class='menu-item-title' and text()='Customers'] ")
	WebElement lnkCustomers_menuItem;
	
	public void clickOnCustomerMenuItem() {
		lnkCustomers_menuItem.click();
	}
	
	@FindBy(xpath="//a[@class='btn bg-blue']")
	WebElement btnAddNew;
	
	public void clickOnAddNew() {
		btnAddNew.click();
	}
	
	@FindBy(id="Email")
	WebElement txtEmail;
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	@FindBy(id="Password")
	WebElement txtPassword;
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	@FindBy(name="FirstName")
	WebElement txtFirstName;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	@FindBy(name="LastName")
	WebElement txtLastName;
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	@FindBy(id="Gender_Male")
	WebElement rdMaleGender;
	
	@FindBy(id="Gender_Female")
	WebElement rdFemaleGender;
	
	public void setGender(String gender) {
		
		if(gender.equals("Male"))
			rdMaleGender.click();
		else
			rdFemaleGender.click();
	}
	
	@FindBy(id="DateOfBirth")
	WebElement txtDateOfBirth;
	
	public void setDateOfBirth(String date) {
		txtDateOfBirth.sendKeys(date);
	}
	
	@FindBy(id="Company")
	WebElement txtCompanyName;
	
	public void setCompanyName(String company) {
		txtCompanyName.sendKeys(company);
	}
	
	@FindBy(id="IsTaxExempt")
	WebElement chkIsTaxExempt;
	
	public void selectIsTaxExempt() {
		chkIsTaxExempt.click();
	}
	
	@FindBy(xpath="//input[@name='SelectedNewsletterSubscriptionStoreIds' and @value='1']")
	WebElement chkYourStoreName_Newsletter;
	
	public void selectYourStoreName() {
		chkYourStoreName_Newsletter.click();
	}
	
	@FindBy(xpath="//input[@name='SelectedNewsletterSubscriptionStoreIds' and @value='2']")
	WebElement chkTextStore2_Newsletter;
	
	public void selectTextStore() {
		chkTextStore2_Newsletter.click();
	}
	
	@FindBy(xpath="//li[text()='Administrators']")
	WebElement lstAdministrator_custRole;
	
	@FindBy(xpath="//li[text()='//li[text()='Forum Moderators']']")
	WebElement lstForumModerators_custRole;
	
	@FindBy(xpath="//li[text()='Guests']")
	WebElement lstGuest_custRole;
	
	@FindBy(xpath="//li[text()='Registered']")
	WebElement lstRegistered_custRole;
	
	@FindBy(xpath="//li[text()='Vendors']")
	WebElement lstVendors_custRole;
	
	public void setCustomerRole(String role) {
		
		if(role.equals("Administrators")) {
			lstAdministrator_custRole.click();
		}
		else if(role.equals("Forum Moderators")) {
			lstForumModerators_custRole.click();
		}
		else if(role.equals("Guests")) {
			lstGuest_custRole.click();
		}
		else if(role.equals("Registered")) {
			lstRegistered_custRole.click();
		}
		else if(role.equals("Vendors")){
			lstVendors_custRole.click();
		}
		
		/*JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);*/
	}
	
	@FindBy(name="VendorId")
	WebElement drpManagerOfVendor;
	
    public void setManagerOfVendor(String role) {
		
		Select sel= new Select(drpManagerOfVendor);
		sel.selectByVisibleText(role);
	}
	
	@FindBy(name="AdminComment")
	WebElement txtAdminComment;
	
	public void setAdminContent(String data) {
		txtAdminComment.sendKeys(data);
	}
	
	@FindBy(name="save")
	WebElement btnSave;
	
	public void clickOnSaveButton() {
		btnSave.click();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageBody() {
		return driver.getPageSource();
	}
	
	
	
	
	
	
	

}
