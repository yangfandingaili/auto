package com.ydd.service.crm;

import com.ydd.page.crm.LoginPage;
import com.ydd.utils.web.WebElementAction;

public class LoginService {
    private LoginPage loginPage = new LoginPage();

    private WebElementAction webElementAction = new WebElementAction();

    public void locateToUrl(String url){
        webElementAction.locatorUrl(url);
}

    public void login(String userName,String validNum) throws Exception {
        try {
            webElementAction.type(loginPage.phoneInput(),userName);
            webElementAction.click(loginPage.validButton());
            webElementAction.type(loginPage.validInput(),validNum);
            webElementAction.click(loginPage.loginButton());
        }catch (Exception e){
            WebElementAction.noSuchElementExceptions.add(e);
            throw  e;
        }
    }

}
