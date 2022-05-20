package com.shopping.testcases;

import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.shopping.BusinessComponents.Components;
import com.shopping.pageObjects.MasterPage;

/*
 * Test Case file
 */

public class OnlineShopping extends MasterPage {
	Components comp;

	/* Below method is triggered only once before the execution of all test cases
	 * Initialize Extent Report
	 * Launch Chrome browser and Load Jupiter Toys application
	 * */
	@BeforeSuite
	public void setUp() {
		comp = new Components();
		comp.initializeReport();
		comp.setDescription("To verify Jupiter Toys Application");
		comp.invokeApplication();
	}

	/* Test Case 1 to verify Mandatory Fields error message before and after entering input value
	 * Navigate to Contact page
	 * Click submit button to verify error message
	 * Enter mandatory fields and verify error message is disappeared
	 */
	@Test
	public void testCase01() {
		comp.setDescription("TC #01 - Verification of Mandatory fields error message in Contact Page");
		comp.navigateToContactPage();
		comp.validateContactPageMandatoryFields();
	}

	/* Test Case 2 to verify 5 successful submission message in Contacts Page
	 * Navigate to Contacts Page
	 * Read 5 set of data from Excel, enter the Mandatory fields, submit to check the success message
	 */
	@Test
	public void testCase02() {
		comp.setDescription("TC #02 - Verification of 5 successful submission message");
		comp.navigateToContactPage();
		comp.validateSubmissionMessage();
	}

	/* Test Case 3 to add products from Shop page and verify the same products in Cart page
	 * Navigate to Shop Page
	 * Confirm if cart is empty before adding new products
	 * Empty the cart if any product is already added to cart
	 * Read products from Excel data file and add to cart in Shop page
	 * Navigate to Cart Page
	 * Verify the selected products are available in cart
	 */
	@Test
	public void testCase03() {
		comp.setDescription("TC #03 - Add products and verify in cart");
		List<String> set = utils.getExcelData("ProductSet1");
		comp.navigateToShopPage();
		comp.addItemsToCart(set);
		comp.navigateToCartPage();
		comp.validateSelectedItemsInCart(set);
	}

	/* TC 4 to add products to cart and verify the subtotal and total price in cart page
	 * Navigate to Shop Page
	 * Read data from Excel and add products to Cart in Shop Page
	 * Before adding products confirm the cart is empty
	 * Navigate to Cart Page
	 * Verify price and quantity of each product matches with its subtotal
	 * Verify sum of subtotal matches with grand final total value
	 */
	@Test
	public void testCase04() {
		comp.setDescription("TC 04 - Add products and verify total price in cart");
		List<String> set = utils.getExcelData("ProductSet2");
		comp.navigateToShopPage();
		comp.addItemsToCart(set);
		comp.navigateToCartPage();
		comp.validatePricingInCart(set);
	}

	/* Below method is executed only once at end of the test case execution
	 * Close the browser and end the Extend Report
	 */
	@AfterSuite
	public void tearDown() {
		comp.closeApplication();
	}

}