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
 * This class file contains the elements and logic for validating the functionalities in Shop Page
 * Inherits attributes from Master page class file 
 */

public class ShopPage extends MasterPage {

	protected By buy_teddyBear = By.xpath("//h4[text()='Teddy Bear']/../p/a[text()='Buy']");
	protected By buy_stuffedFrog = By.xpath("//h4[text()='Stuffed Frog']/../p/a[text()='Buy']");
	protected By buy_handmadeDoll = By.xpath("//h4[text()='Handmade Doll']/../p/a[text()='Buy']");
	protected By buy_fluffyBunny = By.xpath("//h4[text()='Fluffy Bunny']/../p/a[text()='Buy']");
	protected By buy_smileyBear = By.xpath("//h4[text()='Smiley Bear']/../p/a[text()='Buy']");
	protected By buy_funnyCow = By.xpath("//h4[text()='Funny Cow']/../p/a[text()='Buy']");
	protected By buy_valentineBear = By.xpath("//h4[text()='Valentine Bear']/../p/a[text()='Buy']");
	protected By buy_smileyFace = By.xpath("//h4[text()='Smiley Face']/../p/a[text()='Buy']");
	protected By price_teddyBear = By.xpath("//h4[text()='Teddy Bear']/../p/span");
	protected By price_stuffedFrog = By.xpath("//h4[text()='Stuffed Frog']/../p/span");
	protected By price_handmadeDoll = By.xpath("//h4[text()='Handmade Doll']/../p/span");
	protected By price_fluffyBunny = By.xpath("//h4[text()='Fluffy Bunny']/../p/span");
	protected By price_smileyBear = By.xpath("//h4[text()='Smiley Bear']/../p/span");
	protected By price_funnyCow = By.xpath("//h4[text()='Funny Cow']/../p/span");
	protected By price_valentineBear = By.xpath("//h4[text()='Valentine Bear']/../p/span");
	protected By price_smileyFace = By.xpath("//h4[text()='Smiley Face']/../p/span");
	String buyStart = "//h4[text()='";
	String buyEnd = "']/../p/a[text()='Buy']";
	protected CartPage cartPage;

	//Constructor to initialize Cart Page
	public ShopPage(){
		cartPage = new CartPage();
	}

	//Method to navigate to Shop Page
	public void navigateToShopPage() {
		try{
			utils.clickElement(shopMenu, "Shop Menu");
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

	//TC #03, TC #04
	//Method to read data from Excel and add items to the Cart
	//Any number of data can be added with help of Excel
	public void addItemsToCart(List<String> itemName){
		try {
			//Method created in Cart page to check if cart is empty
			cartPage.validateCartIsEmpty();
			//Logic to select 'n' number of products passed as a List of items
			for(int i=0;i<itemName.size();i++) {
				By productName = By.xpath(buyStart+itemName.get(i)+buyEnd);
				utils.clickElement(productName, "Product Item: "+productName);
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