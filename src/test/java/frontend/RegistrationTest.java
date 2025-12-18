package frontend;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void registerNewAccount(){
        webApp.mainPage().goToLoginPage();
        webApp.mainPage().clickRegisterButton();
        webApp.registerPage().registerNewAccount("neshto@gmail.com","12345678","pishmantester");
        Assert.assertTrue(webApp.registrationConfirmPagePage().isResendConfirmMessageButtonDisplayed());
    }
}
