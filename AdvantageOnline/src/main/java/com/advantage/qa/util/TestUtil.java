package com.advantage.qa.util;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil {

	static Xls_Reader reader;

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

	public static ArrayList<Object[]> getDataFromExcel(String sheetName) {

		ArrayList<Object[]> mydata = new ArrayList<Object[]>();
		
		try {
			reader = new Xls_Reader(
					System.getProperty("user.dir") + "/src/main/java/com/advantage/qa/testdata/ShoppingData.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int rownum = 2; rownum <= reader.getRowCount(sheetName); rownum++) {
			
			if(sheetName.equalsIgnoreCase("userregistrationdata"))
			{
				String uname=reader.getCellData("UserRegistrationData", "user_name", rownum);
				String password= reader.getCellData("UserRegistrationData", "password", rownum);
				String confirmPassword=reader.getCellData("UserRegistrationData", "confirm_password", rownum);
				String email=reader.getCellData("UserRegistrationData", "email", rownum);
				String fname=reader.getCellData("UserRegistrationData", "firstname", rownum);
				String lname=reader.getCellData("UserRegistrationData", "lastname", rownum);
				String phonenumber=reader.getCellData("UserRegistrationData", "phonenumber", rownum);			
				Object ob[] = { uname, password,confirmPassword,email,fname,lname,phonenumber };
				mydata.add(ob);
			}
			else if(sheetName.equalsIgnoreCase("logindata"))
			{
				String login_username=reader.getCellData(sheetName, "username", rownum);
				String login_password= reader.getCellData(sheetName, "password", rownum);
				Object ob[] = { login_username, login_password };
				mydata.add(ob);
			}			
			
		}
		return mydata;
	}
}
