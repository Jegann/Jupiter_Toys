package com.shopping.pageObjects;

import org.openqa.selenium.By;

import com.shopping.webDriverUtils.SeleniumFramework;

/*
 * Common class file for storing common methods like launch, login or logout
 */

public class MasterPage {

	protected static By shopMenu = By.id("nav-shop");
	protected By contactMenu = By.id("nav-contact");
	protected static By cartMenu = By.xpath("//li[@id='nav-cart']");
	protected static By noItemsInCart = By.xpath("//li[@id='nav-cart']//span[text()='0']");

	protected SeleniumFramework utils;

	//Constructor to initialize Common Utils class file
	public MasterPage(){
		utils = new SeleniumFramework();
	}

	//Method to launch the application
	public void launchApplication() {
		utils.invokeApplication();
	}

	//Method to close browser
	public void closeBrowser() {
		utils.tearDown();
	}

}