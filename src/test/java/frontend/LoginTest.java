package frontend;

import annotations.TestId;
import base.BaseTest;
import enums.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CSVDataProvider;

public class LoginTest  extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private final String VALID_EMAIL = "malebogia91@gmail.com";

    @Test
    @TestId("TC-002")
    public void testLogIn(){
        logger.info("Navigating to Login Page.");
        webApp.mainPage().openLoginPage();
        logger.info("Authentication: Attempting login for user identity: [{}]", VALID_EMAIL);
        webApp.mainPage().startLogin("malebogia91@gmail.com");

        webApp.loginPage().login("malebogia91@gmail.com", "12345678", Language.EN);

        logger.info("Verification: Checking if the User Panel is visible on the Invoices List Page.");
        Assert.assertTrue(webApp.invoicesListPage().isUserPanelDisplayed());
        logger.info("\"Result: Login verification successful.\"");

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
