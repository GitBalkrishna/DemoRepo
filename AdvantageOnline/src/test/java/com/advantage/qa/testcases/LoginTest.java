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
import com.advantage.qa.util.TestUtil;

public class LoginTest extends TestBase{
	
	public AdvantageLoginPage loginPage;
	public AdvantageHomePage homePage;
	
	@BeforeTest()
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new AdvantageLoginPage();
		homePage = new AdvantageHomePage();		
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testdata = TestUtil.getDataFromExcel("LoginData");
		return testdata.iterator();
	}
	
	@Test(dataProvider = "getTestData")
	public void validateLogin(String username, String pwd)
	{
		loginPage=homePage.clickUserLink();
		homePage=loginPage.advantage_login(username, pwd);
		boolean blnlogin = homePage.validateLogin(username);
		Assert.assertEquals(blnlogin, true, "Login is Not Successfull");
	}
	
	@AfterTest()
	public void tearDown()
	{
		driver.close();
	}

}
