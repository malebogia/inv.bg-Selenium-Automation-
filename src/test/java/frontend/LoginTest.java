package frontend;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CSVDataProvider;

public class LoginTest  extends BaseTest {


    @DataProvider(name = "loginNegativeData")
    public Object[][] loginNegativeData() throws Exception {
        return CSVDataProvider.readCsv(
                "loginNegativeData.csv.csv" ,
                2
        );
    }

    @Test(dataProvider = "loginNegativeData")
    public void tryToLoginWithInvalidCredentials(String email,String password){
        webApp.loginPage().openPage();
        webApp.loginPage().tryToLoginWithInvalidCredentials(email, password);
        Assert.assertTrue(webApp.loginPage().isErrorMessageDisplayed() ||
                webApp.loginPage().isAlertMessageDisplayed());

    }

}
