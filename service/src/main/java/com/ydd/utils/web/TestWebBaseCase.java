package com.ydd.utils.web;

import com.ydd.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public class TestWebBaseCase {
	
	public   static String projectPath = System.getProperty("user.dir");
	
	public  static WebDriver driver = null;
	
	private Log logger=new Log(this.getClass().getSuperclass());
	@Parameters({"brower"})
	@BeforeMethod
	public void initDriver(String brower){
		logger.info("init " +brower+"  webDriver...");
		driver = (new WebDriverConstruction()).getWebDriver(brower);
	}
	
	 @AfterMethod
	    public void quitDriver() {
		 logger.info("close..");
		 if (null!=driver) {
			 driver.quit();
			 logger.info("quit webDriver...");
		}
}
}
