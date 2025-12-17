package frontend;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CSVDataProvider;

public class EnterPageTest extends BaseTest {

    @Test(
         dataProvider = "loginNegativeData",
            dataProviderClass = CSVDataProvider.class
    )
    public void tryToLoginWithInvalidCredentials(String email,String password,String expectedResult){
        webApp.enterPage().openPage();
        webApp.enterPage();
        webApp.enterPage().tryToLoginWithInvalidCredentials(email, password);
        Assert.assertTrue(webApp.enterPage().isErrorMessageDisplayed() ||
                webApp.enterPage().isAlertMessageDisplayed());

    }

}
