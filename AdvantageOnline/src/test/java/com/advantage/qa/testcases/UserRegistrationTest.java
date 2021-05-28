package com.advantage.qa.testcases;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.advantage.qa.base.TestBase;
import com.advantage.qa.pages.AdvantageHomePage;
import com.advantage.qa.pages.AdvantageLoginPage;
import com.advantage.qa.pages.AdvantageRegistrationPage;
import com.advantage.qa.util.TestUtil;

public class UserRegistrationTest extends TestBase {

	public AdvantageLoginPage loginPage;
	public AdvantageHomePage homePage;
	public AdvantageRegistrationPage registerPage;


	@BeforeTest()
	public void setUp() throws InterruptedException {
		initialization();
	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testdata = TestUtil.getDataFromExcel("UserRegistrationData");
		return testdata.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void registerNewUser(String username, String password, String confirmpassword, String emailID,
			String firstName, String lastName, String phoneNumber) throws InterruptedException {
		homePage = new AdvantageHomePage();
		loginPage = homePage.clickUserLink();
		registerPage = loginPage.clickCreateNewLink();
		homePage= registerPage.account_registration(username, password, confirmpassword, emailID, firstName, lastName,
				phoneNumber);		
		 boolean blnRegistration = homePage.validateRegistration(username);
		 Assert.assertEquals(blnRegistration, true, "User Registration "+ username+ "is Not Successfull");
	}

	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
