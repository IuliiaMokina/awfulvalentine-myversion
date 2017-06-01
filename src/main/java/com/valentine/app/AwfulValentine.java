package com.valentine.app;

import com.valentine.tools.Browser;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class AwfulValentine {

private static final String BASE_URL = "http://awful-valentine.com/";

		private static WebDriver driver;

	@Step("Open Home Page by URL: " + BASE_URL)
	public static HomePage openHomePage() {
		driver = Browser.open();
		//driver.manage().window().maximize();
		driver.get(BASE_URL);
		return new HomePage(driver);

	}

	@Step("Close Browser")
	public static void close() {
		if(driver != null) 
			driver.close();
		driver = null;

	}
	
	@Attachment("ScreenShot")
	public static byte[] takeScreenshot(){
		if (driver != null) {
		TakesScreenshot TakesScreenshot = TakesScreenshot.class.cast(driver);
		return TakesScreenshot.getScreenshotAs(OutputType.BYTES);
	}
		return new byte[0];
	}

}