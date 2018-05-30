package gov.usda.tco.atc.glue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.usda.atc.AtcWebElement;

public  class  LIP_Stepdefinition {

	WebDriver driver;
	WebDriverWait wait;
	
	
	@Given("^user log into the LIP application \"([^\"]*)\"$")
	@Test
	public void user_log_into_the_LIP_application(String arg1) throws Throwable {
		System.setProperty("webdriver.gecko.driver",
				"C:\\AutomationDistribution\\oracle\\Selenium Driver\\geckodriver.exe");

		driver = new FirefoxDriver();
		// WebDriverWait wait = new WebDriverWait(driver, 60);// wait statement works
		// like existing in uft

		driver.get("https://cert1-intranet-apps.fsa.usda.gov/lip/login.do");
		
		//String encryptedPass= 
		driver.findElement(By.xpath("//*[@id='USERID']")).sendKeys("****");	
		AtcWebElement.findElement(driver.findElement(By.xpath("//*[@id='PASS']"))).sendSecureKeys("**************");
		driver.findElement(By.xpath("//*[@id=\"ibLogin\"]")).click();

		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='loginSubmit']")));
		if (driver.findElement(By.xpath("//*[@id='loginSubmit']")).isDisplayed()) {
			System.out.println("Login successfull");
			driver.findElement(By.xpath("//*[@id='loginSubmit']")).click();
		} else {
			System.out.println("Login button does not appare.");
		}

	}
	
	@Then("^select state program year from list$")
	@Test
	public void select_state_program_year_from_list() throws Throwable {

		// select state, county and program year from drop down..
		if (driver.findElement(By.id("programYear")).isDisplayed()) {
			Select dropdown = new Select(driver.findElement(By.id("programYear")));
			dropdown.selectByIndex(1);

			Select statecounty = new Select(driver.findElement(By.id("stateCounty")));
			statecounty.selectByVisibleText("Georgia-Appling");

			System.out.println("State and county has selected");
			driver.findElement(By.xpath("//*[@id='continue']")).click();
		} else {
			System.out.println("Unexpected issue has occure. state county has not selected.");
		}
	}

	
	@Then("^Add new application$")
	@Test
	public void add_new_application() throws Throwable {

		// Add or Edit application..
		// WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Add or Edit Application")));
		if (driver.findElement(By.linkText("Add or Edit Application")).isDisplayed()) {
			driver.findElement(By.linkText("Add or Edit Application")).click();
			if (driver.findElement(By.linkText("Delete")).isDisplayed()) {
				driver.findElement(By.linkText("Delete")).click();
				System.out.println("Deleting existing application.");
				wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//input[@title='Delete Application']")));
				driver.findElement(By.xpath("//input[@title='Delete Application']")).click();// deleting application....
																								
				driver.findElement(By.xpath("//input[@value='Add/Search']")).click();

				// SCIMS search..
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("taxId")));
				AtcWebElement.findElement(driver.findElement(By.id("taxId"))).sendSecureKeys("************");
				driver.findElement(By.xpath("//input[@id='last4digitsId']")).click();
				driver.findElement(By.name("Search")).click();
				driver.findElement(By.xpath("/html/body/div/div[4]/div/div/form/table/tbody/tr/td/fieldset/table[1]/tbody/tr[2]/td[3]/font/a")).click();
				System.out.println("Adding a producer to the application");
			} else {
				driver.findElement(By.xpath("//input[@value='Add/Search']")).click();

				// SCIMS search..
				// WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("taxId")));
				AtcWebElement.findElement(driver.findElement(By.id("taxId"))).sendSecureKeys("laGaqU7QxZgC+IPT8NwW6w==");
				driver.findElement(By.xpath("//input[@id='last4digitsId']")).click();
				driver.findElement(By.name("Search")).click();
				driver.findElement(By.xpath("/html/body/div/div[4]/div/div/form/table/tbody/tr/td/fieldset/table[1]/tbody/tr[2]/td[3]/font/a")).click();
				System.out.println("Adding a producer to the application");
			}
		} else {
			System.out.println("Failed during adding producer.");
		}

	}
	
	
	@Then("^Add first Loss of notice information$")
	@Test
	public void add_first_Loss_of_notice_information() throws Throwable {
		// part B notice of Loss

		// WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='nolStartDate']")));
		if (driver.findElement(By.xpath("//input[@id='nolStartDate']")).isDisplayed()) {
			// current date formating...
			// Calendar now = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			// +(now.get(Calendar.YEAR)-1)
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			driver.findElement(By.xpath("//input[@id='nolStartDate']")).sendKeys(currentDate);
			driver.findElement(By.xpath("//input[@id='nolEndDate']")).sendKeys(currentDate);
			driver.findElement(By.xpath("//input[@id='nolApparentDate']")).sendKeys(currentDate);
			driver.findElement(By.xpath("//input[@id='disasterEventAnimal Attack']")).click();
			driver.findElement(By.xpath("//input[@id='disasterEventAnthrax']")).click();
			driver.findElement(By.xpath("//input[@id='disasterEventWinter Storm']")).click();
			driver.findElement(By.xpath("//input[@id='disasterEventStraight Line Winds']")).click();
			driver.findElement(By.xpath("//input[@id='physicalLocationActual']")).sendKeys("Georgia Appling");
			driver.findElement(By.xpath("//input[@id='physicalLocationInventory']")).sendKeys("Georgia Appling");

			Select UnitSelect = new Select(driver.findElement(By.id("livestockUnit")));
			UnitSelect.selectByIndex(1);

			Select signatureType = new Select(driver.findElement(By.id("signatureMethodId")));
			signatureType.selectByIndex(1);

			driver.findElement(By.xpath("//input[@id='nolDisplaySignature']")).sendKeys(currentDate);
			driver.findElement(By.xpath("//input[@id='nolDisplayCocSignature']")).sendKeys(currentDate);
			driver.findElement(By.id("approved")).click();
			driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/form/div[1]/div[8]/input[1]")).click();
			System.out.println("Notice of Loss information has saved properly");

			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/form/div[3]/input[3]")));
			driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/form/div[3]/input[3]")).click();

		} else {
			System.out.println("First notice of loss information page not found.");
		}

	}
	
	
	@Then("^Close browser$")
	@Test
	public void close_browser() throws Throwable {
		driver.close();
		System.out.print("Test completed.");

	}
}
