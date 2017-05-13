package com.valentine.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.valentine.app.AwfulValentine;
import com.valentine.app.HomePage;

public class AddItemToCartTest {

	private HomePage onHomePage;

	@Test

	public void testTheAddCartButtonOpensPopup() {

		onHomePage = AwfulValentine.openHomePage();
		onHomePage.clickAddToCartOnSpecialOffer(1);

		assertTrue(onHomePage.isAddToCartPopupShown(), "'Add to cart' Popup did not appear.");
		
		String productTitle = onHomePage.getPopupProductTitle();
		assertEquals(productTitle, "Closeness and Togetherness", "Incorrect product title on 'Add to cart' Popup");
	}

	@Test(dependsOnMethods = "testTheAddCartButtonOpensPopup")
	public void testAddToCartButtonOnPopupRedirectsToCartPage() {
		onHomePage.clickAddToCartButtonOnPopup();
		assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/store/cart/", "Incorrect URL after click on 'Add to Cart' button");

	}

	
	/*//For Demo puproses only

	@Test(dependsOnMethods = "testAddToCartButtonOnPopupRedirectsToCartPage")

	public void testMultipleConditions() {

		String actualPrice = driver.findElement(By.id("id1")).getText();

		String actualTitle = driver.findElement(By.id("title")).getText();

		String actualDescription = driver.findElement(By.id("desc")).getText();

		

		SoftAssert soft = new SoftAssert();

		soft.assertEquals(actualPrice, "0.77", "Incorrect price");

		soft.assertEquals(actualTitle, "Hello", "Incorrect title");

		

		soft.assertAll();

	}

	*/
	
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
