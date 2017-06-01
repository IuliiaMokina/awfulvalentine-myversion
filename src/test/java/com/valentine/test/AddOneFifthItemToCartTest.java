package com.valentine.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddOneFifthItemToCartTest {

	private WebDriver driver;

	@Test
	public void testTheAddCartButtonOpensPopup() {

		System.setProperty("webdriver.chrome.driver", "C:\\temp\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://awful-valentine.com/");

		driver.findElement(By.cssSelector("[href='#et-offer-post-18']")).click();
		WebElement addToCartPopup = driver.findElement(By.id("fancybox-wrap"));
		Assert.assertTrue(addToCartPopup.isDisplayed(), "'Add to cart' Popup did not appear.");

		waitFor(1000);

		WebElement title = driver.findElement(By.cssSelector("#et-offer-post-18 .et_popup_title"));
		Assert.assertEquals(title.getText(), "Never Forget The Special Day!", "Incorrect product title");
		WebElement price = driver.findElement(By.cssSelector("#cartButtonForm_1 > span:nth-child(4)"));
		Assert.assertEquals(price.getText(), "Price: $99.99", "Incorrect price");

	}

	@Test(dependsOnMethods = "testTheAddCartButtonOpensPopup")
	public void testAddToCartButtonOnPopupRedirectsToCartPage() {

		waitFor(3000);

		driver.findElement(By.id("addToCart_1")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/store/cart/",
				"Incorrect URL after click on 'Add to Cart' button");

	}

	@Test(dependsOnMethods = "testAddToCartButtonOnPopupRedirectsToCartPage")
	public void testAddFifthItemToCartVerifyingTitleAndPrice() {

		waitFor(3000);

		String actualTitle = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(1)"))
				.getText();

		String actualItemPrice = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(3)"))
				.getText();

		String actualItemTotal = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(4)"))
				.getText();

		String actualSubTotal = driver.findElement(By.cssSelector(".subtotal > td:nth-child(4)")).getText();

		String actualShipping = driver.findElement(By.cssSelector(".shipping > td:nth-child(4)")).getText();

		String actualGrandTotal = driver.findElement(By.cssSelector(".grand-total-amount")).getText();

		String actualQuantity = driver.findElement(By.cssSelector(".itemQuantity")).getAttribute("value");
		//int actualQty = Integer.parseInt(actualQuantity);

		String actualShoppingCartSummary = driver.findElement(By.cssSelector("#Cart66WidgetCartEmptyAdvanced"))
				.getText();

		String actualProductTitle = driver.findElement(By.cssSelector(".Cart66ProductTitle")).getText();

		String actualProductSubTotal = driver.findElement(By.cssSelector(".Cart66ProductSubtotal")).getText();

		String actualCartSubTotal = driver.findElement(By.cssSelector(".Cart66Subtotal")).getText();

		SoftAssert soft = new SoftAssert();

		soft.assertEquals(actualTitle, "Never Forget The Special Day!", "Incorrect product title");
		soft.assertEquals(actualItemPrice, "$99.99", "Incorrect item price");
		soft.assertEquals(actualItemTotal, "$99.99", "Incorrect item total");
		soft.assertEquals(actualSubTotal, "$99.99", "Incorrect subtotal");
		soft.assertEquals(actualShipping, "$0.00", "Incorrect shipping");
		soft.assertEquals(actualGrandTotal, "$99.99", "Incorrect grandtotal");

		// Quantity 100 instead 1

		soft.assertEquals(actualQuantity, "1", "Incorrect quantity");

		soft.assertEquals(actualShoppingCartSummary, "You have 1 item ($99.99) in your shopping cart. ",
				"Incorrect count of items");
		soft.assertEquals(actualProductTitle, "Never Forget The Special Day!", "Incorrect product title");
		soft.assertEquals(actualProductSubTotal, "$99.99", "Incorrect productsubtotal");
		soft.assertEquals(actualCartSubTotal, "$99.99", "Incorrect cartsubtotal");

		soft.assertAll();
	}

	@AfterClass

	public void tearDown() {

		driver.close();

	}

	private void waitFor(int milliseconds) {

		try {

			Thread.sleep(milliseconds);

		} catch (Exception e) {

			// TODO: handle exception

		}

	}

}
