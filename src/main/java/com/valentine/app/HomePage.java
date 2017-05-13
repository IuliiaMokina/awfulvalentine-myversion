package com.valentine.app;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

	}
	
	public void addToCartSpecialOffer(int index){
		clickAddToCartOnSpecialOffer
	}
	
	/**
	 * 
	 * @param index
	 */
	public void clickAddToCartOnSpecialOffer(int index) {
		WebElement specialOffer = SpecialOffers().get(index - 1);
		specialOffer.findElement(By.cssSelector(".add-to-cart")).click();
	}
	
	private List<WebElement> SpecialOffers() {
		return driver.findElements(By.className(".special-item"));
	}
	
	public boolean isAddToCartPopupShown() {
		return AddToCartPopup().isDisplayed();
	}
	
	public String getPopupProductTitle(){
		return addToCartPopup().findElement(By.cssSelector(".et_popup_title")).getText();
	}
	
	public void click
}
