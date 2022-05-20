package com.shopping.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shopping.webDriverUtils.Reports;

/*
 * This class file contains the elements and logic for validating the functionalities in Contact Page
 * Inherits attributes from Master page class file 
 */

public class ContactPage extends MasterPage {

	protected final By submit_btn = By.xpath("//a[text()='Submit']");
	protected final By forename_txt = By.id("forename");
	protected final By forenameErr_txt = By.id("forename-err");
	protected final By surname_txt = By.id("surname");
	protected final By email_txt = By.id("email");
	protected final By emailErr_txt = By.id("email-err");
	protected final By telephone_txt = By.id("telephone");
	protected final By message_txt = By.id("message");
	protected final By messageErr_txt = By.id("message-err");
	protected final By submissionSuccess = By.xpath("//div[@class='alert alert-success']");
	protected final By back_btn = By.xpath("//a[contains(text(),'Back')]");

	//Method to navigate to Contact Page
	//try catch blocks used to handle different exceptions
	public void navigateToContactPage() {
		try{
			utils.clickElement(contactMenu, "Contact Menu");
		}catch(TimeoutException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Timeout Exception occured", ExtentColor.RED));
		}catch(NoSuchElementException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("No Such Element Exception occured", ExtentColor.RED));
		}catch(WebDriverException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("WebDriver Exception occured", ExtentColor.RED));
		}catch(Exception e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Exception occured: "+e, ExtentColor.RED));
		}
	}

	//TC #01
	//Method to validate Mandatory Fields error message
	public void validateContactPageMandatoryFields(){
		try {
			//Read the values from excel based on Column Name and store as a List
			List<String> name = utils.getExcelData("Name");
			List<String> email = utils.getExcelData("Email");
			List<String> message = utils.getExcelData("Message");
			//Explicit wait to wait until the element is visible
			utils.waitForElementVisible(submit_btn, 5);
			utils.clickElement(submit_btn, "Submit Button");
			//Condition to check if mandatory error message are appearing
			if(utils.isElementExist(forenameErr_txt) && utils.isElementExist(emailErr_txt) && utils.isElementExist(messageErr_txt)){
				Reports.logger.log(Status.PASS, MarkupHelper.createLabel("All Mandatory field error messages are displayed as expected", ExtentColor.GREEN));
				//Screenshot is captured at corresponding point
				utils.getScreenShot("MandatoryFieldsPresent");
			}
			else
				Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("All Mandatory field error message are not displayed as expected", ExtentColor.RED));
			utils.enterText(forename_txt, name.get(0));
			utils.enterText(email_txt, email.get(0));
			utils.enterText(message_txt, message.get(0));
			//Condition to check if mandatory error messages are disappeared after entering text
			if(utils.isElementExist(forenameErr_txt) && utils.isElementExist(emailErr_txt) && utils.isElementExist(messageErr_txt))
				Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Mandatory field error message is still displayed", ExtentColor.RED));
			else
				Reports.logger.log(Status.PASS, MarkupHelper.createLabel("Mandatory field error message is not displayed", ExtentColor.GREEN));
			utils.getScreenShot("MandatoryFieldsRemoved");
		}catch(TimeoutException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Timeout Exception occured", ExtentColor.RED));
		}catch(NoSuchElementException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("No Such Element Exception occured", ExtentColor.RED));
		}catch(WebDriverException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("WebDriver Exception occured", ExtentColor.RED));
		}catch(Exception e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Exception occured: "+e, ExtentColor.RED));
		}
	}

	//TC #02
	//Method to validate successful submission message for n number of users.
	//Adding 'n' number of data to Excel Sheet, this method will be executed 
	public void validateSubmissionMessage(){
		try {
			//add wait condition
			List<String> name = utils.getExcelData("Name");
			List<String> email = utils.getExcelData("Email");
			List<String> message = utils.getExcelData("Message");
			for(int i=0;i<name.size();i++) {
				utils.waitForElementVisible(submit_btn, 30);
				utils.enterText(forename_txt, name.get(i));
				utils.enterText(email_txt, email.get(i));
				utils.enterText(message_txt, message.get(i));
				utils.clickElement(submit_btn, "Submit Button");
				utils.waitForElementVisible(submissionSuccess, 30);
				//Condition to check submission success message after submitting
				if(utils.isElementExist(submissionSuccess)) {
					Reports.logger.log(Status.PASS, MarkupHelper.createLabel("Submission success message is displayed for user "+name.get(i), ExtentColor.GREEN));
					utils.getScreenShot("FeedbackSubmitted");
					utils.clickElement(back_btn, "Back button");
				}
				else
					Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Submission success message is not displayed", ExtentColor.RED));
			}
		}catch(TimeoutException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Timeout Exception occured", ExtentColor.RED));
		}catch(NoSuchElementException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("No Such Element Exception occured", ExtentColor.RED));
		}catch(WebDriverException e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("WebDriver Exception occured", ExtentColor.RED));
		}catch(Exception e){
			Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Exception occured: "+e, ExtentColor.RED));
		}
	}

}
