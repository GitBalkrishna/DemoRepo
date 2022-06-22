package com.computer.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.computer.qa.base.TestBase;
import com.computer.qa.pages.ComputersHomePage;
import com.computer.qa.pages.EditComputerPage;
import com.computer.qa.util.TestUtil;

public class SearchComputerTest extends TestBase {

	public ComputersHomePage computersHomePage;
	public EditComputerPage editComputerPage;

	
	@BeforeClass()
	public void setUp() throws InterruptedException {
		initialization();
		computersHomePage = new ComputersHomePage();
		computersHomePage.validateLaunch();
	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testdata = TestUtil.getDataFromExcel("SearchComputer");
		return testdata.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void testSearchByfilter(String computerName) {
		computersHomePage.validateSearch(computerName);
	}

	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
