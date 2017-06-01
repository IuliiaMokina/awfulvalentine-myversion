package com.valentine.app;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class PurchaseFormsPage {

	private WebDriver driver;
	
	public PurchaseFormsPage (WebDriver driver) {
		this.driver = driver;
		new WebDriverWait(driver, 10).until(urlContains("purchase-forms"));
		PageFactory.initElements(driver, this);
			
	}
	
	@Step("Read current URL")
	@Attachment("URL")
	public String getCurrentUrl() {
		return driver.getCurrentUrl();

	}
	
}
