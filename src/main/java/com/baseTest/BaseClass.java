package com.baseTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.actitime.generics.FWUtils;

public class BaseClass {
	static
	{
		System.setProperty(CHROM_KEY,CHROM_VALUE);
		System.setProperty(GECKO_KEY,GECKO_VALUE);
	}
	
	public static WebDriver driver;
	public static int passCount=0,failCount=0;
	
	@BeforeClass
	@Parameters("sBrowser")
	public void openBrowser(String sBrowser)
	{
		if(sBrowser.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(sBrowser.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(sBrowser.equals("ie"))
		{
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@BeforeMethod
	public void openApplication()
	{
		driver.get(URL);
		
	}
	@AfterMethod
	public void closeBrowser(ITestResult res)
	{
		
		int status = res.getStatus();
		String methodname = res.getName();
		if(status==1)
		{
			passCount++;
		}
		else
		{			
			failCount++;			
			String path = PHOTO_PATH+methodname+".png";
			FWUtils.take_Screen_Shoot(driver,path);
		}
		
		driver.close();
	}
	
	@AfterSuite
	public void printReport() {
		Reporter.log("PassCount:"+passCount,true);
		Reporter.log("FailCount:"+failCount,true);
		FWUtils.set_XL_Data(REPORT_PATH, "result",1,0,passCount);
		FWUtils.set_XL_Data(REPORT_PATH, "result",1,1,failCount);
	}
	

}


}
