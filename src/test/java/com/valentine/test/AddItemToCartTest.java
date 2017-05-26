package com.valentine.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.valentine.app.AwfulValentine;
import com.valentine.app.HomePage;
import com.valentine.app.ShoppingCartPage;
import com.valentine.data.ProductDataModel;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Shopping")
@Stories("Add Item to Cart")
public class AddItemToCartTest {
	private HomePage onHomePage;
	private ShoppingCartPage onShoppingCartPage;
	private ProductDataModel testItem;

	@Test
	public void testTheAddCartButtonOpensPopup() {
		onHomePage = AwfulValentine.openHomePage();
		waitFor(3000);
		int randomIndex = new Random().nextInt(5) + 1;
		testItem = onHomePage.getSpecialOffer(randomIndex);

		onHomePage.clickAddToCartOnSpecialOffer(randomIndex);

		//onHomePage.clickAddToCartOnSpecialOffer(testItem);
		//onHomePage.clickAddToCartOnSpecialOffer(1);

		assertTrue(onHomePage.isAddToCartPopupShown(), "'Add to cart' Popup did not appear.");
		//
		// String productTitle = onHomePage.getPopupProductTitle();
		// double productPrice = onHomePage.getPopupProductPrice();
		//
		// assertEquals(productTitle, testItem.getTitle(), "Incorrect product
		// title on 'Add to Cart' popup");
		// assertEquals(productPrice, testItem.getUnitPrice(), "Incorrect
		// product price on 'Add to Cart' popup");

		// assertEquals(onHomePage.getProductInfoFromPopup(), testItem,
		// "Incorrect data on popup");
		productsShouldBeEqual(onHomePage.getProductInfoFromPopup(), testItem);
		}
	
	@Test(dependsOnMethods = "testTheAddCartButtonOpensPopup")
	public void testAddToCartButtonOnPopupRedirectsToCartPage() {
		waitFor(1000);
		onShoppingCartPage = onHomePage.clickAddToCartButtonOnPopup();
		assertEquals(onShoppingCartPage.getCurrentUrl(), "http://awful-valentine.com/store/cart/",
				"Incorrect URL after click on 'Add to Cart' button");
	}
	
	@AfterClass
	public void tearDown() {
		AwfulValentine.close();
	}
	
	@Step("Product details on Popup should be equal to product details on selected item")
	private void productsShouldBeEqual(ProductDataModel actual, ProductDataModel expected) {
		String message = "";

		if (!actual.getTitle().equals(expected.getTitle())) {
			message = message.concat("Expected product title: " + expected.getTitle());
			message = message.concat("\nActual product title: " + actual.getTitle());
		}

		if (actual.getUnitPrice() != expected.getUnitPrice()) {
			message = message.concat("\nExpected product price: " + expected.getUnitPrice());
			message = message.concat("\nActual product price: " + actual.getUnitPrice());
		}

		if (!message.equals(""))
			throw new AssertionError(message);
	}

	private void waitFor(int milliseconds) {

		try {

			Thread.sleep(milliseconds);

		} catch (Exception e) {

			// TODO: handle exception

		}

	}
}