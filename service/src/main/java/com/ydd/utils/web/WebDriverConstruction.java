package com.ydd.utils.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverConstruction {
	private WebDriver driver=null;
	private String projectPath = System.getProperty("user.dir")+ "/driver/";
	
	public WebDriver getWebDriver(String browser){
		if("fireFox".equals(browser)){
			System.setProperty("webdriver.firefox.marionette",projectPath + "geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if("chrome".equals(browser)){
			System.setProperty("webdriver.chrome.driver",projectPath + "chromedriver.exe"); 
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if("ie".equals(browser)){
			System.setProperty("webdriver.ie.driver",projectPath + "IEDriverServer.exe"); 
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
		}
		else
			return null;
		return this.driver;
	}
}
