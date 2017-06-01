package com.valentine.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class AddFifthItemToCartTest {

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
	public void testAddSecondItemToCartVerifyingTitlePrice() {

		waitFor(1000);

		WebElement title = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(1)"));
		Assert.assertEquals(title.getText(), "Never Forget The Special Day!", "Incorrect product title");

		WebElement itemprice = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(3)"));
		Assert.assertEquals(itemprice.getText(), "$99.99", "Incorrect item price");

		WebElement itemtotal = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(4)"));
		Assert.assertEquals(itemtotal.getText(), "$99.99", "Incorrect item total");

		WebElement subtotal = driver.findElement(By.cssSelector(".subtotal > td:nth-child(4)"));
		Assert.assertEquals(subtotal.getText(), "$99.99", "Incorrect subtotal");

		WebElement shipping = driver.findElement(By.cssSelector(".shipping > td:nth-child(4)"));
		Assert.assertEquals(shipping.getText(), "$0.00", "Incorrect shipping");

		WebElement grandtotal = driver.findElement(By.cssSelector(".grand-total-amount"));
		Assert.assertEquals(grandtotal.getText(), "$99.99", "Incorrect grandtotal");

		WebElement quantity = driver.findElement(By.cssSelector(".itemQuantity"));
		Assert.assertEquals(quantity.getText(), "1", "Incorrect quantity");

		String shoppingCartSummary = driver.findElement(By.id("#Cart66WidgetCartEmptyAdvanced")).getText();
		Assert.assertTrue(shoppingCartSummary.startsWith("You have 1 item"), "Incorrect count of items");

		WebElement producttitle = driver.findElement(By.cssSelector(".Cart66ProductTitle"));
		Assert.assertEquals(producttitle.getText(), "Never Forget The Special Day!", "Incorrect product title");

		WebElement productsubtotal = driver.findElement(By.cssSelector(".Cart66ProductSubtotal"));
		Assert.assertEquals(productsubtotal.getText(), "$99.99", "Incorrect productsubtotal");

		WebElement cartsubtotal = driver.findElement(By.cssSelector(".Cart66Subtotal"));
		Assert.assertEquals(cartsubtotal.getText(), "$99.99", "Incorrect cartsubtotal");

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
