package frontend;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.base.WebApp;
import pages.frontend.EnterPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLogIn(){
        webApp.loginPage().goToLoginPage();
        webApp.loginPage().login("malebogia91@gmail.com");
        webApp.enterPage().login("malebogia91@gmail.com","12345678", EnterPage.Language.EN);



    }



}
