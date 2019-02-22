package com.ydd.page.crm;
import java.io.IOException;
import java.io.InputStream;
import com.ydd.utils.BaseAction;
import import com.ydd.utils.Locator;
import com.ydd.pageObjectConfig.PageObjectAutoCodeForXml;//中台CRM登录页面_对象库类
public class LoginPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/ydd/crm/pageObjectConfig/UILibrary.xml";
 public   LoginPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* 输入手机号输入框
* @return
* @throws IOException
*/
public  Locator phoneInput() throws IOException
 {
   Locator locator=getLocator("phoneInput");
   return locator;
 }

/***
* 输入验证码输入框
* @return
* @throws IOException
*/
public  Locator validInput() throws IOException
 {
   Locator locator=getLocator("validInput");
   return locator;
 }

/***
* 获取验证码按钮
* @return
* @throws IOException
*/
public  Locator validButton() throws IOException
 {
   Locator locator=getLocator("validButton");
   return locator;
 }

/***
* 登录按钮
* @return
* @throws IOException
*/
public  Locator loginButton() throws IOException
 {
   Locator locator=getLocator("loginButton");
   return locator;
 }
}