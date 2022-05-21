package com.shopping.pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.shopping.webDriverUtils.Reports;

/*
 * This class file contains the elements and logic for validating the functionalities in Cart Page
 * Inherits attributes from Master page class file 
 */

public class CartPage extends MasterPage {

	protected final By emptyCart_btn = By.xpath("//a[text()='Empty Cart']");
	protected final By emptyCart_confirm_btn = By.xpath("//div[@class='modal-footer']//a[text()='Yes']");
	protected final By total = By.xpath("(//tr/td[1])[4]/strong");
	protected final String productQuantityStart = "//tbody/tr/td[contains(text(),'";
	protected final String productQuantityEnd = "')]/..//input[@name='quantity']";
	protected final String productPriceStart = "//tbody/tr/td[contains(text(),'";
	protected final String productPriceMid = "')]/../td[";
	protected final String productSubtotalStart = "//tbody/tr/td[contains(text(),'";
	protected final String productSubtotalMid = "')]/../td[";
	protected final String productEnd = "]";
	protected final By headerSubTotal = By.xpath("//thead/tr/th[text()='Subtotal']/preceding-sibling::th");
	protected final By headerPrice = By.xpath("//thead/tr/th[text()='Price']/preceding-sibling::th");
	protected final By colHeaders = By.xpath("//thead//th");

	//Method to navigate to Cart Page
	public void navigateToCartPage() {
		try{
			utils.clickElement(cartMenu, "Cart Icon");
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

	//TC #03
	//Method to verify if Cart is Empty
	public void validateCartIsEmpty(){
		try {
			//Condition to check if cart is empty and remove items if any items found
			if(utils.isElementExist(noItemsInCart))
				Reports.logger.log(Status.PASS, MarkupHelper.createLabel("No items available in cart", ExtentColor.GREEN));
			else{
				utils.clickElement(cartMenu, "Cart Menu");
				utils.waitForElementVisible(emptyCart_btn, 30);
				utils.clickElement(emptyCart_btn, "Empty Cart button");
				utils.waitForElementVisible(emptyCart_confirm_btn, 30);
				utils.clickElement(emptyCart_confirm_btn, "Confirm Empty Cart button");
				if(utils.isElementExist(noItemsInCart)) {
					Reports.logger.log(Status.PASS, MarkupHelper.createLabel("No items available in cart", ExtentColor.GREEN));
					utils.clickElement(shopMenu, "Shop Menu");
				}
				else
					Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Cart is still not empty", ExtentColor.RED));
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

	//TC #03
	//Method to verify if selected items are present in the cart
	public void validateSelectedItemsInCart(List<String> productName){
		try {
			//Convert List to Map to get the total quantity of each product
			List<String> secondaryList = new ArrayList<String>();
			secondaryList.addAll(productName);
			HashMap<String, Integer> hmap = new HashMap<String, Integer>();
			for (String str : secondaryList) {
				int count = 0;
				count = Collections.frequency(secondaryList, str);
				hmap.put(str, count);
			}
			//Logic to compare the product name and total quantity selected is present in the cart
			for(Map.Entry<String, Integer> itemName: hmap.entrySet()){
				By productQuantity = By.xpath(productQuantityStart+itemName.getKey()+productQuantityEnd);
				int qty = Integer.parseInt(utils.getAttributeByValue(productQuantity, "value"));
				if(qty == itemName.getValue())
					Reports.logger.log(Status.PASS, MarkupHelper.createLabel("Selected Product and quantity matches in the cart. Product: "+itemName.getKey()+" Quantiy: "+qty, ExtentColor.GREEN));
				else
					Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Selected Product and quantity not matching in the cart. Product: "+itemName.getKey()+" Quantiy: "+qty, ExtentColor.RED));

			}
			utils.getScreenShot("ProductAndQuantityCheck");
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

	//TC #04
	//Method to validate subtotal and total values in the Cart
	public void validatePricingInCart(List<String> itemList){
		try {
			int subtotalIndex = (utils.getWebDriver().findElements(headerSubTotal).size())+1;
			int priceIndex = (utils.getWebDriver().findElements(headerPrice).size())+1;
			
			List<Double> subtotalAll = new ArrayList<Double>();
			double subtotal = 0;
			List<String> secondaryList = new ArrayList<String>();
			secondaryList.addAll(itemList);
			//Convert List to Map to get the total quantity of each product
			HashMap<String, Integer> hmap = new HashMap<String, Integer>();
			for (String str : secondaryList) {
				int count = 0;
				count = Collections.frequency(secondaryList, str);
				hmap.put(str, count);
			}
			for(Map.Entry<String, Integer> itemName: hmap.entrySet()){
				//Create WebElement for each row ItemName and convert to Double
				By productQuantity = By.xpath(productQuantityStart+itemName.getKey()+productQuantityEnd);
				double qty = Double.parseDouble(utils.getAttributeByValue(productQuantity, "value"));
				//Create WebElement for each row Price and convert to Double
				By productPrice = By.xpath(productPriceStart+itemName.getKey()+productPriceMid+priceIndex+productEnd);
				double price = Double.parseDouble(utils.getText(productPrice).replaceAll("[^0-9.]", ""));
				//Create WebElement for each row Quantity and convert to Double
				By productSubtotal = By.xpath(productSubtotalStart+itemName.getKey()+productSubtotalMid+subtotalIndex+productEnd);
				subtotal = Double.parseDouble(utils.getText(productSubtotal).replaceAll("[^0-9.]", ""));
				//Logic to check quantity and price matching sub total of each item
				if(qty * price == subtotal)
					Reports.logger.log(Status.PASS, MarkupHelper.createLabel("Subtotal matches for Product: "+itemName.getKey()+" Quantiy: "+qty+". Price: "+". Subtotal: "+subtotal, ExtentColor.GREEN));
				else
					Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Subtotal not matching for Product: "+itemName.getKey()+" Quantiy: "+qty+". Price: "+". Subtotal: "+subtotal, ExtentColor.RED));
				//Add all the subtotal to a List
				subtotalAll.add(subtotal);
			}
			//Method to calculate sum of each product's subtotal
			double subtotalGrandValue = sumAllValues(subtotalAll);
			double totalValue = Double.parseDouble(utils.getText(total).split(": ")[1]);
			//Logic to check subtotal of each item is matching with total value
			if(subtotalGrandValue == totalValue)
				Reports.logger.log(Status.PASS, MarkupHelper.createLabel("Sum of subtotal matches with total value. Sum of subtotal: "+subtotalGrandValue+". Total: "+totalValue, ExtentColor.GREEN));
			else
				Reports.logger.log(Status.FAIL, MarkupHelper.createLabel("Sum of subtotal not matches with total value. Sum of subtotal: "+subtotalGrandValue+". Total: "+totalValue, ExtentColor.RED));
			utils.getScreenShot("ProductAndQuantityCheck");
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

	//Method to calculate sum of each subtotal
	public double sumAllValues(List<Double> subtotalAll){
		double finalTotal = 0;
		for(double subtot: subtotalAll){
			finalTotal += subtot;
		}
		return finalTotal;
	}
}
