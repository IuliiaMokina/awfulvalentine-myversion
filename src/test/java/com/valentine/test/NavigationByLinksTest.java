package com.valentine.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import com.valentine.app.AwfulValentine;
//import com.valentine.app.CodePage;
//import com.valentine.app.HomePage;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//@Features("Navigation")
//@Stories("Navigation with using Links")
public class NavigationByLinksTest {

	// private HomePage onHomePage;
	// private CodePage onCodePage;

	// @BeforeClass
	// public void setup() {
	// onHomePage = AwfulValentine.openHomePage();

	private WebDriver driver;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\temp\\chromedriver.exe");
		//driver = new ChromeDriver();
		//driver.get("http://awful-valentine.com/");
	}
	
	@BeforeMethod
	public void HomePage() {
		driver = new ChromeDriver();
		driver.get("http://awful-valentine.com/");
	}

	@Test
	public void NavigationToCodePage() {

		driver.findElement(By.cssSelector("#top-menu > li:nth-child(2)")).click();

		assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/code/",
				"Incorrect URL after click on 'Code' link");
	}

	@Test
	public void NavigationToContactUsPage() {

		driver.findElement(By.cssSelector("#top-menu > li:nth-child(3)")).click();

		assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/contact-us/",
				"Incorrect URL after click on 'Contact Us' link");
	}

	@Test
	public void NavigationToPurchaseFormPage() {
		
		driver.findElement(By.cssSelector("#top-menu > li:nth-child(4)")).click();

		assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/purchase-forms/",
				"Incorrect URL after click on 'Purchase Form' link");
	}
	
	@Test
	public void NavigationToStorePage() {
		
		driver.findElement(By.cssSelector("#top-menu > li:nth-child(5)")).click();

		assertEquals(driver.getCurrentUrl(), "http://awful-valentine.com/store/",
				"Incorrect URL after click on 'Store' link");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}

// @Test
// public void NavigationToCodePage() {

// onCodePage = onHomePage.clickCodeLink();

// assertEquals(onCodePage.getCurrentUrl(), "http://awful-valentine.com/code/",
// "Incorrect URL after click on 'Code' link");
// }

// @AfterClass(alwaysRun = true)
// public void tearDown() {
// AwfulValentine.close();
// }
// }
