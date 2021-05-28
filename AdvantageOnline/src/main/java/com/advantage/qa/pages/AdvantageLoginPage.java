package com.advantage.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.advantage.qa.base.TestBase;

public class AdvantageLoginPage extends TestBase {

	// Page Factory -OR
	@FindBy(xpath = "//input[@name='username']")
	WebElement input_UserName;

	@FindBy(xpath = "//input[@name='password']")
	WebElement input_Password;
	
	@FindBy(xpath = "//button[@id='sign_in_btnundefined']")
	WebElement btn_Signin;
	
	@FindBy(xpath = "//div[@class='login ng-scope']//a[text()='CREATE NEW ACCOUNT']")
	WebElement link_CreateAccount;	
	
	
	public AdvantageLoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public AdvantageHomePage advantage_login(String username, String pwd) {
		input_UserName.sendKeys(username);
		input_Password.sendKeys(pwd);
		WebDriverWait w = new WebDriverWait(driver, 10);
		WebElement btnsignin = w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='sign_in_btnundefined']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", btnsignin);
		return new AdvantageHomePage();
	}
	public AdvantageRegistrationPage clickCreateNewLink() throws InterruptedException
	{
		WebDriverWait w = new WebDriverWait(driver, 20);
		WebElement element = w.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login ng-scope']//a[text()='CREATE NEW ACCOUNT']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
      	return new AdvantageRegistrationPage();
	}

}