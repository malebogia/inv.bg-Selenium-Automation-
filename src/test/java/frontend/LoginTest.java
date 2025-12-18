package frontend;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CSVDataProvider;

public class LoginTest  extends BaseTest {

    @Test(
         dataProvider = "loginNegativeData",
            dataProviderClass = CSVDataProvider.class
    )
    public void tryToLoginWithInvalidCredentials(String email,String password,String expectedResult){
        webApp.loginPage().openPage();
        webApp.loginPage().tryToLoginWithInvalidCredentials(email, password);
        Assert.assertTrue(webApp.loginPage().isErrorMessageDisplayed() ||
                webApp.loginPage().isAlertMessageDisplayed());

    }

}
