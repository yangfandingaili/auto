package com.ydd.cases;

import com.ydd.utils.mobile.TestMobileBaseCase;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Login extends TestMobileBaseCase {
    @Test
    public void login() throws InterruptedException {
      driver.launchApp();
        WebElement my = driver.findElementByName("我的");
        
        Thread.sleep(5000);
        my.click();
    }
}
