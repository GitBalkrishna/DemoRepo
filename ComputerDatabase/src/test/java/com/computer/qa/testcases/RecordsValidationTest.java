package com.computer.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.computer.qa.base.TestBase;
import com.computer.qa.pages.ComputersHomePage;

public class RecordsValidationTest extends TestBase {

	public ComputersHomePage computersHomePage;

	@BeforeClass()
	public void setUp() throws InterruptedException {
		initialization();
		computersHomePage = new ComputersHomePage();
		computersHomePage.validateLaunch();
	}
	
	@Test
	public void testNumberOfRecordsInSinglePage()
	{
	    computersHomePage.validateNumberOfRecordInSinglePage();
	}
	
	@Test
	public void testColumnsInTable()
	{
		computersHomePage.validateNumberOfColumnsInTable();
	}
	
	@Test
	public void testNumberofComputersFound()
	{
		
		computersHomePage.validateNumberOfComputersFound(574);
	}
	
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
