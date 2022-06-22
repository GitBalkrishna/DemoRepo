package com.computer.qa.pages;

import static com.computer.qa.util.TestUtil.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.computer.qa.base.TestBase;

public class AddComputerPage extends TestBase {

	// Add Computer Page Factory -OR
	@FindBy(xpath = "//input[@id='name']")
	WebElement txt_computername;

	@FindBy(xpath = "//input[@id='introduced']")
	WebElement txt_introduced;

	@FindBy(xpath = "//input[@id='discontinued']")
	WebElement txt_discountinued;

	@FindBy(id = "company")
	WebElement sel_company;

	@FindBy(xpath = "//input[@value='Create this computer']")
	WebElement btn_createcomputer;

	public AddComputerPage() {
		PageFactory.initElements(driver, this);
	}

	public ComputersHomePage enterDetailsToAddComputer(String computername, String introduced, String discontinue,
			String company) {
		clickElement(driver,txt_computername);
		enterData(driver, txt_computername, computername);
		enterData(driver, txt_introduced, introduced);
		enterData(driver, txt_discountinued, discontinue);
		clickElement(driver,sel_company);
		Select option = new Select(sel_company);
		option.selectByVisibleText(company);
		clickElement(driver,btn_createcomputer);
		return new ComputersHomePage();
	}

}
