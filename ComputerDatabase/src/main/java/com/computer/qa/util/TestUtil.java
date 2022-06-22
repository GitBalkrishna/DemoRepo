package com.computer.qa.util;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

public class TestUtil {

	static Excel_Reader reader;

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 20;

	public static boolean waitForElement(WebDriver browser_driver, WebElement ele, int max_wait_time) {
		try {
			WebDriverWait wait = new WebDriverWait(browser_driver, max_wait_time);
			wait.until(ExpectedConditions.visibilityOf(ele));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean WaitUntilElementNotVisible(WebDriver browser_driver, WebElement ele, int max_wait_time) {
		try {
			WebDriverWait wait = new WebDriverWait(browser_driver, max_wait_time);
			wait.until(ExpectedConditions.invisibilityOf(ele));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static ArrayList<Object[]> getDataFromExcel(String sheetName) {

		ArrayList<Object[]> mydata = new ArrayList<Object[]>();

		try {
			reader = new Excel_Reader(
					System.getProperty("user.dir") + "/src/main/java/com/computer/qa/testdata/ComputerDatabase.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int rownum = 2; rownum <= reader.getRowCount(sheetName); rownum++) {

			if (sheetName.equalsIgnoreCase("addcomputer") || sheetName.equalsIgnoreCase("modifycomputerdetails") ) {
				String computername = reader.getCellData(sheetName, "computername", rownum);
				String introduced = reader.getCellData(sheetName, "introduced", rownum);
				String discontinued = reader.getCellData(sheetName, "discontinued", rownum);
				String company = reader.getCellData(sheetName, "company", rownum);
				Object ob[] = { computername, introduced, discontinued, company };
				mydata.add(ob);
			} else if (sheetName.equalsIgnoreCase("searchcomputer")) {
				String computername = reader.getCellData(sheetName, "computername", rownum);
				Object ob[] = { computername };
				mydata.add(ob);
			}			

		}
		return mydata;
	}

	public static void clickElement(WebDriver driver,WebElement ele) {
		try {	
			waitForElement(driver, ele, 5);
			ele.click();
		} catch (Exception e) {
			new AssertionError("Element " + ele + " is not Clicked::" + e);
		}

	}

	public static void enterData(WebDriver browser_driver, WebElement ele, String text) {
		try {
			waitForElement(browser_driver,ele,5);
			ele.clear();
			ele.sendKeys(text);
		} catch (Exception e) {
			new AssertionError("Not able to Enter Data for Element" + ele + "Got Exception:" + e);
		}

	}

}
