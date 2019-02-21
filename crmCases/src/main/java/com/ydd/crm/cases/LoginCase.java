package com.ydd.crm.cases;

import com.ydd.service.crm.LoginService;
import com.ydd.utils.web.TestWebBaseCase;
import com.ydd.utils.web.WebAssertion;
import org.testng.annotations.Test;

public class LoginCase extends TestWebBaseCase {

    private LoginService loginService = new LoginService();
    @Test(description = "登录")
    public void testLogin() throws Exception {
            loginService.locateToUrl("http://qastatic.yundada56.com/crm/index.html#/ocean/public");
            loginService.login("18652911562","123456");
            WebAssertion.VerityError();

    }
}
