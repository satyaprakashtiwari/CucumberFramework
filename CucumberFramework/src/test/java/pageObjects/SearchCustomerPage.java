package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.WaitHelper;

public class SearchCustomerPage {

	public WebDriver driver;

	public WaitHelper waitHelper;

	public SearchCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}

	@FindBy(id = "SearchEmail")
	WebElement txtEmail;

	public void setEmail(String email) {
		//waitHelper.waitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	@FindBy(name = "SearchCompany")
	WebElement txtCompany;

	public void setCompany(String company) {
		txtCompany.sendKeys(company);
	}

	@FindBy(id = "SearchFirstName")
	WebElement txtFirstName;

	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	@FindBy(id = "SearchLastName")
	WebElement txtLastName;

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	@FindBy(id = "SearchMonthOfBirth")
	WebElement drpMonth_DOB;

	public void setDate(String month) {

		Select sel = new Select(drpMonth_DOB);
		sel.selectByVisibleText(month);
	}

	@FindBy(id = "SearchDayOfBirth")
	WebElement drpDay_DOB;

	public void setDay(String day) {

		Select sel = new Select(drpDay_DOB);
		sel.selectByVisibleText(day);
	}

	@FindBy(id="search-customers")
	WebElement btnSearch;
	
	public void clickSearch() {
		btnSearch.click();
	}
	
	@FindBy(xpath="//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;
	
	public int getNumberOfRows() {
		return tableRows.size();
	}
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;
	
	public int getNumberOfColumns() {
		return tableColumns.size();
	}
	
	@FindBy(xpath="//table[@id='customers-grid']//tr/td[2]")
	WebElement emailID;
	
	public String getData(WebElement element) {
		return element.getText();
	}
	
	public boolean searchCustomerByEmail(String email) {
		
		boolean flag= false;
		
		for(int i=1;i<=getNumberOfRows();i++) {
			
			String emailid= driver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[2]")).getText();
			System.out.println("Email ID is: "+emailid);
			
			if(emailid.equals(email))
				flag=true;
			
		}
		
		return flag;
	}
	
    public boolean searchCustomerByName(String fullname) {
    	
    	String[] nameWithFirstAndLast=fullname.split(" ");
    	String fname=nameWithFirstAndLast[0];
    	String lname=nameWithFirstAndLast[1];
		
		boolean flag= false;
		
		for(int i=1;i<=getNumberOfRows();i++) {
			
			String name= driver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[3]")).getText();
			System.out.println("Nane is: "+name);
			String[] names=name.split(" ");
			
			if(names[0].equals(fname) && names[1].equals(lname))
				flag=true;
			
		}
		
		return flag;
	}

	@FindBy(tagName="a")
	List<WebElement> links;
	
	public void getAllLinks() {
		
		for(WebElement link: links) {
			
			System.out.println(link.getAttribute("href"));
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
