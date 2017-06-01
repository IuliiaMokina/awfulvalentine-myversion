package com.valentine.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.valentine.app.AwfulValentine;
import com.valentine.app.CodePage;
import com.valentine.app.ContactUsPage;
import com.valentine.app.HomePage;
import com.valentine.app.PurchaseFormsPage;
import com.valentine.app.StorePage;

import ru.yandex.qatools.allure.annotations.Features;

@Features("Navigation")
public class NavigationRefactoredTest {

	private HomePage onHomePage;
	private CodePage onCodePage;
	private ContactUsPage onContactUsPage;
	private PurchaseFormsPage onPurchaseFormsPage;
	private StorePage onStorePage;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\temp\\chromedriver.exe");
	}
	
	@BeforeMethod
	public void HomePage() {
		onHomePage = AwfulValentine.openHomePage();
		
	}
	
	@Test
	public void testClickCodeLink() {
		onCodePage = onHomePage.clickCodeLink();
		
		assertEquals(onCodePage.getCurrentUrl(), "http://awful-valentine.com/code/",
				"Incorrect URL after click on 'Code' link");
	}
	
	@Test
	public void testClickContactUsLink() {
		onContactUsPage = onHomePage.clickContactUsLink();
		
		assertEquals(onContactUsPage.getCurrentUrl(), "http://awful-valentine.com/contact-us/",
				"Incorrect URL after click on 'Contact Us' link");
	}
	
	@Test
	public void navigationToPurchaseFormPage() {
		onPurchaseFormsPage = onHomePage.clickPurchaseFormsLink();
		
		assertEquals(onPurchaseFormsPage.getCurrentUrl(), "http://awful-valentine.com/purchase-forms/",
				"Incorrect URL after click on 'Purchase Form' link");
	}
	
	@Test
	public void navigationToStorePage() {
		onStorePage = onHomePage.clickStoreLink();
		
		assertEquals(onStorePage.getCurrentUrl(), "http://awful-valentine.com/store/",
				"Incorrect URL after click on 'Store' link");
	}
	
	@AfterMethod
	public void tearDown() {
		AwfulValentine.close();
	}
}
