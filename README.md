# Jupiter_Toys
Jupiter Toys automation project

Readme File

Framework name - Data Driven Framework
Testing Framework - TestNG
Build Management Tool - Maven
Browser - Chrome
Report - Extent Report

Trigger point:
1. Run as TestNG from OnlineShopping.java
2. Run as TestNG from testng.xml
3. Application URL can be changed in config.properties file to check the validation for Sprint2

Reports path - JupiterToys\test-output\ExtentReports

Common Utilities:
ExcelUtils.java - Read data from Excel
SeleniumFramework.java - Common utilities like driver initialization, re-usable methods
Reports - Extent Report creation and end
pom.xml - To add Maven dependencies

Test Cases:
OnlineShopping.java - All 4 test cases are defined here with TestNG annotation

Page Objects:
CartPage.java - Elements and logic implementation of Cart page
ContactPage.java - Elements and logic implementation of Contact page
ShopPage.java - Elements and logic implementation of Shop page
MasterPage.java - Common elements in the application and common functionalities like login, logout

Business Components:
Components.java - A single class file to maintain and navigate all the logics behind this automation project

Data files
Shopping_Data.xlsx - Excel file to read input data for each test case
config.properties - Another external file to read data. Application URL is given here
