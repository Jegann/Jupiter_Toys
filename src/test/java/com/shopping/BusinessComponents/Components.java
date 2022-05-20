package com.shopping.BusinessComponents;

import java.util.List;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shopping.pageObjects.*;
import com.shopping.webDriverUtils.Reports;


/* 
 * This class file comprises of all the methods definitions for each pages in the application
 */
public class Components {

	//Method to launch Chrome browser and load application 
	public void invokeApplication() {
		MasterPage masterPage = new MasterPage();
		masterPage.launchApplication();
		Reports.logger.log(Status.PASS, MarkupHelper.createLabel("Application Launched", ExtentColor.GREEN));
	}

	//Method to initiate Extend Reports
	public void initializeReport() {
		Reports Reports = new Reports();
		Reports.initializeReport();
	}

	//Method to describe the scope of each report
	public void setDescription(String desc) {
		Reports Reports = new Reports();
		Reports.setTestCaseDescription(desc);
	}

	//Method to close the browser and Extend Report
	public void closeApplication(){
		MasterPage masterPage = new MasterPage();
		masterPage.closeBrowser();
		Reports Reports = new Reports();
		Reports.closeReport();
	}

	//Method to navigate to Contact page
	public void navigateToContactPage() {
		ContactPage contact = new ContactPage();
		contact.navigateToContactPage();
	}

	//Method to validate mandatory fields error message in Contact page - TC #01
	public void validateContactPageMandatoryFields() {
		ContactPage contact = new ContactPage();
		contact.validateContactPageMandatoryFields();
	}

	//Method to validate successful submission message in Contact page - TC #02 
	public void validateSubmissionMessage() {
		ContactPage contact = new ContactPage();
		contact.validateSubmissionMessage();
	}

	//Method to navigate to Shop Page
	public void navigateToShopPage() {
		ShopPage shop = new ShopPage();
		shop.navigateToShopPage();
	}

	//Method to read data from Excel and add items to the cart - TC #03, TC #04
	public void addItemsToCart(List<String> set) {
		ShopPage shop = new ShopPage();
		shop.addItemsToCart(set);
	}

	///Method to navigate to Cart Page
	public void navigateToCartPage() {
		CartPage cart = new CartPage();
		cart.navigateToCartPage();
	}

	//Method to validate selected items in the cart - TC #03
	public void validateSelectedItemsInCart(List<String> set) {
		CartPage cart = new CartPage();
		cart.validateSelectedItemsInCart(set);
	}

	//Method to validate price of each item in the cart - TC #04
	public void validatePricingInCart(List<String> set) {
		CartPage cart = new CartPage();
		cart.validatePricingInCart(set);
	}
}