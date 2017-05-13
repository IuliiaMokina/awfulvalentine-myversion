package com.valentine.app;

import com.valentine.tools.Browser;
import org.openqa.selenium.WebDriver;

public class AwfulValentine {

	private static WebDriver driver;
	public static HomePage openHomePage() {

		WebDriver driver = Browser.open();

		driver.get("http://awful-valentine.com/");

		return new HomePage(driver);
	}

	public static void close(){
		if(driver != null)
			driver.close();
		driver = null;
	}
}
