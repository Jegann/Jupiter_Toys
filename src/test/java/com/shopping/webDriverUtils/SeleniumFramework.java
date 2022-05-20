package com.shopping.webDriverUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

/*
 * Class File to store common reusable methods
 */

public class SeleniumFramework extends ExcelUtils{

	private static WebDriver driver = null;
	public Properties prop;
	private String filename = "config.properties";

	//Constructor to initialize property file
	public SeleniumFramework() {
		prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(filename));
			readExcelFile();
		} catch (Exception e) {
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Property file is not loaded", ExtentColor.RED));
			e.printStackTrace();
		}
	}

	//Method to use driver object across the project
	public WebDriver getWebDriver() {
		return driver;
	}

	//Method to launch Chrome browser and load application URL
	public void invokeApplication() {
		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(prop.getProperty("App_Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	//Method to click an Element
	public void clickElement(By elem, String elementName) {
		try {
			driver.findElement(elem).click();
			Reports.logger.log(Status.PASS, MarkupHelper.createLabel(elementName+" is clicked", ExtentColor.GREEN));
		} catch (Exception e) {
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Fail to click " + elem, ExtentColor.RED));
		}
	}

	//Method to get text of an element
	public String getText(By elem) {
		String value = null;
		try {
			value = driver.findElement(elem).getText().trim();
		} catch (Exception e) {
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Element is not present " + elem, ExtentColor.RED));
		}
		return value;
	}

	//Method to enter a text to an text box
	public void enterText(By elem, String value) {
		try {
			driver.findElement(elem).clear();
			driver.findElement(elem).sendKeys(value);
			Reports.logger.log(Status.PASS, MarkupHelper.createLabel(value+" is entered", ExtentColor.GREEN));
		} catch (Exception e) {
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Element is not present " + elem, ExtentColor.RED));
		}
	}

	//Method to wait for an element to be visible
	public void waitForElementVisible(By elem, int sec) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
		} catch (Exception e) {
			e.printStackTrace();
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Fail Element is not present " + elem, ExtentColor.RED));
		}
	}

	//Method to check if an element is present or not
	public boolean isElementExist(By element){
		try {
			if(driver.findElement(element).isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	//Method to take screenshot
	public String getScreenShot(String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		Reports.logger.log(Status.INFO, "Screenshot from : " + destination).addScreenCaptureFromPath(destination);
		return destination;
	}

	public void tearDown() {
		driver.quit();
	}

	//Method to get any attribute of an element
	public String getAttributeByValue(By element, String value){
		String text = null;
		try {
			text = driver.findElement(element).getAttribute(value.toLowerCase());
		} catch (Exception e) {
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Exception in getting attribute", ExtentColor.RED));
		}
		return text;
	}

}