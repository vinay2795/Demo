package com.actiTime.scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DemoA {
	static {
		System.setProperty("webdriver.chrome.driver","./src/test/resources/driver/chromedriver.exe");
	}

	@Test
	public void testBrowser() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
	}
}
