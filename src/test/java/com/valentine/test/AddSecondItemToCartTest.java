package com.valentine.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class AddSecondItemToCartTest {

	private WebDriver driver;

	@Test

	public void testTheAddCartButtonOpensPopup() {

		System.setProperty("webdriver.chrome.driver", "C:\\temp\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("http://awful-valentine.com/");

		driver.findElement(By.cssSelector("[href='#et-offer-post-27']")).click();

		WebElement addToCartPopup = driver.findElement(By.id("fancybox-wrap"));

		Assert.assertTrue(addToCartPopup.isDisplayed(), "'Add to cart' Popup did not appear.");

		waitFor(1000);

		WebElement title = driver.findElement(By.cssSelector("#et-offer-post-27 .et_popup_title"));

		Assert.assertEquals(title.getText(), "Lets Grow Old Together!", "Incorrect product title");

		WebElement price = driver.findElement(By.cssSelector("#cartButtonForm_5_2 > span:nth-child(4)"));

		Assert.assertEquals(price.getText(), "Price: $88.79", "Incorrect price");

	}

	@Test(dependsOnMethods = "testTheAddCartButtonOpensPopup")

	public void testAddToCartButtonOnPopupRedirectsToCartPage() {

		waitFor(3000);

		driver.findElement(By.id("addToCart_5_2")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/store/cart/",
				"Incorrect URL after click on 'Add to Cart' button");

	}

	@Test(dependsOnMethods = "testAddToCartButtonOnPopupRedirectsToCartPage")

	public void ttestAddSecondItemToCartVerifyingTitle() {

		waitFor(1000);

		WebElement title = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(1)"));

		Assert.assertEquals(title.getText(), "Lets Grow Old Together!", "Incorrect product title");

	}

	@Test(dependsOnMethods = "testAddToCartButtonOnPopupRedirectsToCartPage")

	public void ttestAddSecondItemToCartVerifyingPrice() {

		waitFor(1000);

		WebElement itemprice = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(3)"));

		Assert.assertEquals(itemprice.getText(), "$88.79", "Incorrect item price");

		WebElement itemtotal = driver
				.findElement(By.cssSelector("#viewCartTable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(4)"));

		Assert.assertEquals(itemtotal.getText(), "$88.79", "Incorrect item total");

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
