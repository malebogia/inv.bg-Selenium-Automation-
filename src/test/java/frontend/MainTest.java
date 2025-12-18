package frontend;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.frontend.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLogIn(){
        webApp.mainPage().goToLoginPage();
        webApp.mainPage().login("malebogia91@gmail.com");
        webApp.loginPage().login("malebogia91@gmail.com","12345678", LoginPage.Language.EN);



    }



}
