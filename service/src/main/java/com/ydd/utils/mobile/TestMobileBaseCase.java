package com.ydd.utils.mobile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.ydd.utils.Log;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
/**
 *  
 * @author yangfan
 *
 */
public class TestMobileBaseCase {
	public static AndroidDriver<WebElement> driver;
	//方法描述
	public static String description;
	public Log log=new Log(this.getClass().getSuperclass());
	public static  String appPackage;//当前启动的app包名
	public static String appActivity;//当前启动app的入口
	public static MobileElementAction action=new MobileElementAction();
	@BeforeTest
	@Parameters({"driverName","nodeURL","appName","deviceId","deviceName","sdkVersion","appMainPackage","appActivity","platformName"})
	public void  setup( String driverName,String nodeURL,String appName,String deviceId,String deviceName,String sdkVersion,String appMainPackage,String appActivity,String platformName) throws MalformedURLException {
		log.info("------------------开始执行测试---------------");
		TestMobileBaseCase.appPackage = appMainPackage;
		TestMobileBaseCase.appActivity = appActivity;
		//启动appium server

		log.info("通过cmd命令启动appium server");
		try {
			String cmd="appium -a " +
					nodeURL.split(":")[0]+
					"  -p "+nodeURL.split(":")[1]+" -U "+deviceId;
			System.out.println(cmd);
			action.executeCmd(cmd);
			action.sleep(10);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(nodeURL.equals("")||nodeURL.isEmpty())
		{
			log.error("appium url 没有设置");
		}
		else {
			log.info("读取xml配置：Mobile Driver:"+driverName+"；Appium Server:"+"http://"+nodeURL+"/wd/hub"+"设备Id："+deviceId+"设备名字："+deviceName);
			try {
				Process process=Runtime.getRuntime().exec("cmd.exe /c appium");
				System.out.println(process.toString());
				 this.driver=setRemoteDriver(driverName, nodeURL, appName, deviceId,deviceName, sdkVersion, appMainPackage, appActivity,platformName);
				 log.info(driver.getCapabilities().toString());
				System.out.println(driver.manage().logs().toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				log.error("appium环境配置失败");
			}
		}

	}

	@AfterTest
	public void tearDown() throws IOException {
		try{
			Thread.sleep(2000);
			driver.quit();
//		driver.closeApp();
		}catch (Exception e){
			log.error(e.getMessage());
		}finally {
			log.info("关闭appium server");
			//关闭启动appium的窗口进程
			action.executeCmd("taskkill /f /im conhost.exe");
			//关闭appium服务对应的进程
			action.executeCmd("taskkill /f /im node.exe");
			log.info("-------------结束测试，并关闭退出driver及appium server-------------");
		}
	}
	/**
	 *
	 * @param driverName driverName
	 * @param nodeURL   appium URL
	 * @param appName   app文件名
	 * @param deviceName 设备名字
	 * @param sdkVersion 安卓版本
	 * @param appMainPackage app主包名
	 * @param appActivity app主类名
	 * @param platformName 设备平台    真机：Android AVD虚拟设备：Android Emulator
	 * @return
	 * @throws MalformedURLException
	 */
	private AndroidDriver<WebElement> setRemoteDriver(String driverName,String nodeURL,String appName,String devieceId,String deviceName,String sdkVersion,String appMainPackage,String appActivity,String platformName) throws MalformedURLException
	{
		//获取app路径
		System.out.println(System.getProperty("user.dir"));
		File classRootPath=new File(System.getProperty("user.dir"));
		File appDir=new File(classRootPath,"apps");
		File app =new File(appDir,appName);
		DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
		switch (driverName)
		{
			case "AndroidDriver" :
				//设置自动化相关参数

				desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,platformName);
				desiredCapabilities.setCapability(MobileCapabilityType.UDID,devieceId );
				//devcieName为必输项，但是值没有意思，可以随便填写
				desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName );
				desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, sdkVersion);
				//设置app路径，
//				desiredCapabilities.setCapability("app", app.getAbsolutePath());
				//设置app主包名和主类名

				desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appMainPackage);
				desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);

				//设置支持中文输入
				desiredCapabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, "True");
				desiredCapabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, "True");
				desiredCapabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, "True");
				desiredCapabilities.setCapability("autoLaunch", false);
//				desiredCapabilities.setCapability("fullReset", false);
				//初始化driver
				driver= new AndroidDriver<WebElement>(new URL("http://"+nodeURL+"/wd/hub"), desiredCapabilities);
				break;
				//初始化H5 driver
			case "Webapp":
				desiredCapabilities.setCapability("platformName",platformName);
				desiredCapabilities.setCapability("uuid",devieceId );
				desiredCapabilities.setCapability("deviceName",deviceName );
				desiredCapabilities.setCapability("platformVersion", sdkVersion);
				//设置app路径
//				desiredCapabilities.setCapability("app", app.getAbsolutePath());
				//设置app主包名和主类名
				desiredCapabilities.setCapability("appPackage", appMainPackage);
				desiredCapabilities.setCapability("appActivity", appActivity);
				//appWaitActivity：app启动后真正的activity名称
				desiredCapabilities.setCapability("appWaitActivity",appActivity);
				//设置支持中文输入
				desiredCapabilities.setCapability("unicodeKeyboard", "True");
				//将输入法重置为设置原有的输入法，unicodeKeyboard和resetKeyboard成对使用
				desiredCapabilities.setCapability("resetKeyboard", "True");
				//不重签名app，签名后app可能会打不开
				desiredCapabilities.setCapability("noSign", "True");
				//如果是H5的测试，需要加上此句
				desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chorme");
//				desiredCapabilities.setCapability("autoLaunch", false);
				//初始化driver
				driver= new AndroidDriver<WebElement>(new URL("http://"+nodeURL+"/wd/hub"), desiredCapabilities);
				break;
			default:
				//获取app路径
				File classRootPath2=new File(System.getProperty("usr.dir"));
				File appDir2=new File(classRootPath2,"apps");
				File app2 =new File(appDir2,appName);
				//设置自动化相关参数
				DesiredCapabilities desiredCapabilities2=new DesiredCapabilities();
				desiredCapabilities2.setCapability("platformName", "Android");
				desiredCapabilities2.setCapability("udid",devieceId );
				desiredCapabilities2.setCapability("deviceName",deviceName );
				desiredCapabilities2.setCapability("platformVersion", sdkVersion);
				//设置app路径
				desiredCapabilities2.setCapability("app", app2.getAbsolutePath());
				//设置app主包名和主类名
				desiredCapabilities2.setCapability("appMainPackage", appMainPackage);
				desiredCapabilities2.setCapability("appActivity", appActivity);


				//设置支持中文输入
				desiredCapabilities2.setCapability("unicodeKeyboard", "True");
				desiredCapabilities2.setCapability("resetKeyboard", "True");
				desiredCapabilities2.setCapability("noSign", "True");
				//初始化driver
				driver= new AndroidDriver<WebElement>(new URL("http://"+nodeURL+"/wd/hub"), desiredCapabilities2);
				break;
		}
		return driver;
	}
	public static void main(String args[])
	{
		WebDriver driver2=new ChromeDriver();
		driver2.get("http://www.baidu.com");
	}


}
