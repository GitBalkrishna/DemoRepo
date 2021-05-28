package com.advantage.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.advantage.qa.base.TestBase;
import com.advantage.qa.util.TestUtil;

public class AdvantageRegistrationPage extends TestBase {
	// Page Factory -OR
	@FindBy(name = "usernameRegisterPage")
	WebElement input_username;

	@FindBy(xpath = "//input[@name='passwordRegisterPage']")
	WebElement input_password;

	@FindBy(xpath = "//input[@name='confirm_passwordRegisterPage']")
	WebElement input_confirmpassword;

	@FindBy(xpath = "//input[@name='emailRegisterPage']")
	WebElement input_email;

	@FindBy(xpath = "//input[@name='first_nameRegisterPage']")
	WebElement input_firstname;

	@FindBy(xpath = "//input[@name='last_nameRegisterPage']")
	WebElement input_lastname;

	@FindBy(xpath = "//input[@name='phone_numberRegisterPage']")
	WebElement input_phonenumber;

	@FindBy(xpath = "//input[@name='i_agree']")
	WebElement checkbox_agreePrivacyNotice;

	@FindBy(xpath = "//button[@id='register_btnundefined']")
	WebElement button_register;

	// Initializing the Page Objects
	public AdvantageRegistrationPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public AdvantageHomePage account_registration(String username, String password, String confirmPassword,
			String emailID, String firstName, String lastName, String phoneNumber) {
		TestUtil.waitForElement(driver, input_username, 30);
		input_username.sendKeys(username);
		input_password.sendKeys(password);
		input_confirmpassword.sendKeys(confirmPassword);
		input_email.sendKeys(emailID);
		input_firstname.sendKeys(firstName);
		input_lastname.sendKeys(lastName);
		input_phonenumber.sendKeys(String.valueOf(phoneNumber));
		checkbox_agreePrivacyNotice.click();
		WebDriverWait w = new WebDriverWait(driver, 20);
		WebElement reg_ele = w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='register_btnundefined']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", reg_ele);
		boolean bln_register = w.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='register_btnundefined']")));
		Assert.assertEquals(bln_register, true, "User Registration is Not Successfull");
		return new AdvantageHomePage();
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
