package com.computer.qa.testcases;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.computer.qa.base.TestBase;
import com.computer.qa.pages.AddComputerPage;
import com.computer.qa.pages.ComputersHomePage;
import com.computer.qa.util.TestUtil;

public class AddComputerTest extends TestBase {

	public ComputersHomePage homePage;
	public AddComputerPage addComputerPage;

	
	@BeforeClass()
	public void setUp() throws InterruptedException {
		initialization();
		homePage = new ComputersHomePage();
		homePage.validateLaunch();
	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testdata = TestUtil.getDataFromExcel("AddComputer");
		return testdata.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void validateAddComputer(String computername, String introduced, String discontinue, String company) {
		addComputerPage = homePage.clickAddNewComputer();
		homePage = addComputerPage.enterDetailsToAddComputer(computername, introduced, discontinue, company);
		homePage.validateNewComputerAdded(computername);
	}

	
	@AfterClass()
	public void tearDown() {
		driver.close();
	}

}
