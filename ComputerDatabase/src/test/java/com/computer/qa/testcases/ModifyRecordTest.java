package com.computer.qa.testcases;

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

public class ModifyRecordTest extends TestBase {

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
		ArrayList<Object[]> testdata = TestUtil.getDataFromExcel("ModifyComputerDetails");
		return testdata.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void testModifyComputer(String computername, String introduced, String discontinue, String company) throws InterruptedException {
		editComputerPage = computersHomePage.clickComputer(computername);
		computersHomePage=editComputerPage.modifyComputer(computername, introduced, discontinue, company);
		computersHomePage.validateComputerModified(computername);
	}

	
	@AfterClass()
	public void tearDown() {
		driver.close();
	}

}
