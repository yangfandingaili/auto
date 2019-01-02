package com.ydd.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class InitAPPDriver {
	public static AndroidDriver<WebElement> initDriver (File file,String appWaitActivity,String appActivity,String appPackage) throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//		desiredCapabilities.setCapability("app", file);
		desiredCapabilities.setCapability("deviceName", "127.0.0.1:62001");
		//等待新命令超时时间，单位是秒，默认是60秒.
//		当appium服务在指定的超时时间内未接收到任何来自客户端的指令，appium服务端与客户端会主动断开链接
//		desiredCapabilities.setCapability("newCommandTimeout", 60);
		/**
		 * 在uiautomator模式下跳过检查和应用的重签名, 默认是 false
		 */
		desiredCapabilities.setCapability("noSign", true);
		desiredCapabilities.setCapability("appWaitActivity", appWaitActivity);
		/**
		 * 设置appiun启动后是否自动安装和启动app应用。
		 * 默认值是true(当这个值是true时，代码里则不能使用driver.launchApp方法)
		 */
		/**
		 * 链接物理指定设备的唯一标识（安卓上就是通过adb devic1
		 * es看到的设备名称）
		 * 如果连接多台设置的时候，需要告诉appium连接哪台设置
		 */
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, "127.0.0.1:62001");
		desiredCapabilities.setCapability("appActivity", appActivity);
		desiredCapabilities.setCapability("appPackage", appPackage);
		AndroidDriver<WebElement> adAndroidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		return adAndroidDriver;
	}
}
