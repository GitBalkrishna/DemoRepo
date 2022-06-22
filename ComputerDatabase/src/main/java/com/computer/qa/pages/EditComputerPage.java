package com.computer.qa.pages;

import static com.computer.qa.util.TestUtil.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.computer.qa.base.TestBase;

public class EditComputerPage extends TestBase {
	
	// Page Factory -OR
	@FindBy(xpath = "//input[@id='name']")
	WebElement txt_computername;

	@FindBy(xpath = "//input[@id='introduced']")
	WebElement txt_introduced;

	@FindBy(xpath = "//input[@id='discontinued']")
	WebElement txt_discountinued;

	@FindBy(id = "company")
	WebElement opt_company;
	
	@FindBy(xpath = "//input[@value='Save this computer']")
	WebElement btn_savethecomputer;
	
		
	// Initializing the Page Objects
	public EditComputerPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public ComputersHomePage modifyComputer(String computername, String introduced, String discontinue,String company) throws InterruptedException {
		waitForElement(driver, btn_savethecomputer, 20);
		enterData(driver, txt_introduced, introduced);
		enterData(driver,txt_discountinued,discontinue);
		clickElement(driver,opt_company);
		Select option = new Select(opt_company);
		option.selectByVisibleText(company);
		clickElement(driver, btn_savethecomputer);
		return new ComputersHomePage();
	}

	/*public void waitForElementEnabled(WebElement ele) {
		try {
			WebDriverWait w = new WebDriverWait(driver, 20);
			w.until((ExpectedCondition<Boolean>) driver -> ele.isEnabled());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

}
