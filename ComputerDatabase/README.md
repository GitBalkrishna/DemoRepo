Project is About Storing the Details of Computer and Able to Add New Computer, Search for a Particular Computer and Modify

Page Object Model Framework Used to Automate the Manual Test Cases.

Core Java and Selenium Library is used to develop automation Scripts.

TestNG is used for Managing and Execution of Tests.

Maven is used for Dependency Management of Libraries and it has to be Configured Locally by downloading Apache Maven.

Apache POI Library is used for interacting with Excel - Excel is used for test data management.

Extent Report is used for Reporting.

Steps to Execute the Tests:

Download ChromeDriver exe file as per the Chrome Application Installed in your Machine/VM and Copy it to ComputerDatabase=>Drivers

From IDE(Eclipse,Intellij or Any Other)- Right Click on the testng.xml file Present at the Project Root Folder(Computer Database) and Select Run As => TestNG Suite

From Console - 
Open CMD and Go to Project Root Directory(Computer Database)
Execute Command "mvn test -DsuiteXmlFile=testng.xml"
 
Once Execution is Complete, 
Extent Report will be Generated at Location: Root Project Folder(ComputerDatabase)=>test-output=>ComputerExecutionReport.html

TestNG Report will be Generated at Location:Root Project Folder(ComputerDatabase)=>test-output=>emailable-report.html
