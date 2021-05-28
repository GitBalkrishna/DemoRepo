package com.advantage.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.advantage.qa.pages.AdvantageLoginPage;
import com.advantage.qa.util.TestUtil;
import com.advantage.qa.base.TestBase;

public class AdvantageHomePage extends TestBase {

	// Page Factory -OR
	@FindBy(id = "menuUserLink")
	WebElement link_UserLink;

	@FindBy(xpath = "//a[@id='menuUserLink']/span")
	WebElement txt_User;

	public AdvantageHomePage() {
		PageFactory.initElements(driver, this);
	}

	public AdvantageLoginPage clickUserLink() {
		TestUtil.waitForElement(driver, link_UserLink, 30);
		link_UserLink.click();
		return new AdvantageLoginPage();

	}

	public boolean validateLogin(String username) {
		WebElement user = driver.findElement(By.xpath("//a[@id='menuUserLink']//span[text()='" + username + "']"));
		boolean blnlogin = TestUtil.waitForElement(driver, user, 30);
		return blnlogin;
	}

	public boolean validateRegistration(String UserName) {
		return txt_User.getText().equalsIgnoreCase(UserName);
	}

}
