package frontend;

import base.BaseTest;
import enums.Language;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CSVDataProvider;

public class LoginTest  extends BaseTest {

    private final String VALID_EMAIL = "malebogia91@gmail.com";

    @Test
    public void testLogIn(){
        webApp.mainPage().openLoginPage();
        webApp.mainPage().startLogin("malebogia91@gmail.com");
        webApp.loginPage().login("malebogia91@gmail.com", "12345678", Language.EN);
        Assert.assertTrue(webApp.invoicesListPage().isUserPanelDisplayed());

    }


    @DataProvider(name = "loginNegativeData")
    public Object[][] loginNegativeData() throws Exception {
        return CSVDataProvider.readCsv(
                "loginNegativeData.csv.csv" ,
                2
        );
    }

    @Test(dataProvider = "loginNegativeData")
    public void tryToLoginWithInvalidCredentials(String email,String password){
        webApp.mainPage().openLoginPage();
        webApp.mainPage().startLogin(VALID_EMAIL);
        webApp.loginPage().clearEmail();
        webApp.loginPage().login(email,password,Language.EN);
   }

}
