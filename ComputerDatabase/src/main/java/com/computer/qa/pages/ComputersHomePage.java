package com.computer.qa.pages;

import static com.computer.qa.util.TestUtil.WaitUntilElementNotVisible;
import static com.computer.qa.util.TestUtil.clickElement;
import static com.computer.qa.util.TestUtil.waitForElement;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.computer.qa.base.TestBase;

public class ComputersHomePage extends TestBase {

	// Page Factory -OR
	@FindBy(id = "searchsubmit")
	WebElement btn_filterByName;

	@FindBy(id = "searchbox")
	WebElement txt_SearchBox;

	@FindBy(xpath = "//a[@id='add']")
	WebElement btn_AddNewComputer;

	@FindBy(xpath = "//a[text()='Computer database']")
	WebElement link_ComputerDatabase;

	@FindBy(xpath = "//section[@id='main']/div[@class='alert-message warning']")
	WebElement txt_newComputerAlertMsg;

	@FindBy(xpath = "//table[@class='computers zebra-striped']//tbody")
	WebElement table_computer;

	@FindBy(xpath = "//table[@class='computers zebra-striped']//tbody//tr")
	List<WebElement> table_rows;

	@FindBy(xpath = "//table[@class='computers zebra-striped']//thead//tr/th")
	List<WebElement> table_columns;

	@FindBy(xpath = "//table[@class='computers zebra-striped']//tbody//tr//a")
	WebElement link_ComputerName;

	@FindBy(xpath = "//section[@id='main']//h1")
	WebElement txt_ComputersFound;

	@FindBy(xpath = "//div[@id='pagination']//ul/li[1]/a")
	WebElement link_Previous;

	@FindBy(xpath = "//div[@id='pagination']//ul/li[3]/a")
	WebElement link_Next;

	@FindBy(xpath = "//div[@id='pagination']//ul/li[2]")
	WebElement txt_Current;

	public ComputersHomePage() {
		PageFactory.initElements(driver, this);
	}

	public void validateLaunch() {
		boolean status = waitForElement(driver, btn_filterByName, 30);
		assertTrue(status, "Launch is Not Successful");
	}

	public AddComputerPage clickAddNewComputer() {
		waitForElement(driver, btn_AddNewComputer, 5);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", btn_AddNewComputer);
		WaitUntilElementNotVisible(driver, btn_AddNewComputer, 5);
		return new AddComputerPage();
	}

	public void validateComputerModified(String computername) {
		String computerCreatedMsg = txt_newComputerAlertMsg.getText();
		String expectedMsg = "Done ! Computer " + computername + " has been updated";
		assertEquals(computerCreatedMsg, expectedMsg, "Computer Details are Not Modified Successfully");
	}

	public void validateNewComputerAdded(String computername) {
		String computerCreatedMsg = txt_newComputerAlertMsg.getText();
		String expectedMsg = "Done ! Computer " + computername + " has been created";
		assertEquals(computerCreatedMsg, expectedMsg, "New Computer Details are Not Added");
	}

	public ComputersHomePage clickComputerDatabase() {
		clickElement(driver, link_ComputerDatabase);
		return new ComputersHomePage();
	}

	public EditComputerPage clickComputer(String computerName) {
		txt_SearchBox.sendKeys(computerName);
		clickElement(driver, btn_filterByName);
		waitForElement(driver, table_computer, 10);
		if (table_rows.size() == 0)
			assertFalse(table_rows.size() == 0, "Computer Record is Not Found");
		if (table_rows.size() == 1) {
			clickElement(driver, link_ComputerName);
		} else {
			for (int i = 1; i <= table_rows.size(); i++) {
				String ComputerName = driver
						.findElement(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[" + i + "]//td[1]"))
						.getText();
				if (ComputerName.equals(computerName)) {
					WebElement computerLink = driver.findElement(
							By.xpath("//table[@class='computers zebra-striped']//tbody//tr[" + i + "]//td[1]//a"));
					JavascriptExecutor je = (JavascriptExecutor) driver;
					je.executeScript("arguments[0].click();", computerLink);
				}
			}
		}
		return new EditComputerPage();
	}

	public void validateSearch(String computerName) {
		txt_SearchBox.sendKeys(computerName);
		clickElement(driver, btn_filterByName);
		waitForElement(driver, table_computer, 10);
		assertTrue(table_rows.size() == 1, "Computer is not found in the Database");
	}

	public void validateNumberOfRecordInSinglePage() {
		waitForElement(driver, table_computer, 5);
		assertTrue(table_rows.size() == 10, "Records In a Single Page is Not Equals To 10");
	}

	public void validateNumberOfColumnsInTable() {
		ArrayList<String> columns = new ArrayList<String>();
		columns.add(0, "Computer name");
		columns.add(1, "Introduced");
		columns.add(2, "Discontinued");
		columns.add(3, "Company");
		waitForElement(driver, table_computer, 5);
		assertTrue(table_columns.size() == 4, "Number of Columns in a Table is Not Equals to 4");
		for (int i = 0; i < table_columns.size(); i++) {
			assertEquals(table_columns.get(i).getText(), columns.get(i), "Column Name Mismatch");
		}
	}

	public void validateNumberOfComputersFound(int numberOfComputers) {
		String computersFoundText = txt_ComputersFound.getText();
		int number_of_Computers = Integer.parseInt(computersFoundText.split(" ")[0]);
		String actual = number_of_Computers + " " + "computers found";
		String expected = numberOfComputers + " " + "computers found";
		assertEquals(actual, expected, " Number of Computers Found Mismatch");
	}

	public void validateNextLinkisEnabled() {
		boolean bln_next = link_Next.isEnabled();
		assertTrue(bln_next, "Next Link is Disabled");
	}

	public void validatePagination() {
		waitForElement(driver, link_Next, 10);
		assertTrue(table_rows.size() == 10, "Total Records are not Equals to 10");
		int number_of_Computers = Integer.parseInt(txt_ComputersFound.getText().split(" ")[0]);
		int from_number = Integer.parseInt(txt_Current.getText().split(" ")[1]);
		int to_number=Integer.parseInt(txt_Current.getText().split(" ")[3]);
		while (to_number < number_of_Computers) {			
			clickElement(driver, link_Next);
			waitForElement(driver, link_Next, 10);
			from_number = Integer.parseInt(txt_Current.getText().split(" ")[1]);
		    to_number=Integer.parseInt(txt_Current.getText().split(" ")[3]);
			assertTrue(table_rows.size() == (to_number-from_number)+1,
					"Records in a Single Page is Not Matching - " + txt_Current.getText() + "");
			
		}

	}

}
