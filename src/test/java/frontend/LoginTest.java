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
        webApp.mainPage().startLogin(VALID_EMAIL);

        logger.info("Authentication: Submitting valid credentials");
        webApp.loginPage().login(VALID_EMAIL, "12345678", Language.EN);

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
    @TestId("TC-003")
    public void tryToLoginWithInvalidCredentials(String email,String password) throws InterruptedException {
        logger.warn("Login attempt throttling may occur due to repeated invalid credentials");

        logger.info("Navigating to login page");
        webApp.mainPage().openLoginPage();

        logger.info("Authentication: Initiating login flow");
        webApp.mainPage().startLogin(VALID_EMAIL);

        logger.info("Test Setup: Clearing pre-filled email to execute negative login scenario");
        webApp.loginPage().clearEmail();

        logger.info("Authentication: Attempting login with invalid credentials [email={}]", email);
        webApp.loginPage().login(email,password,Language.EN);

        Assert.assertTrue(webApp.loginPage().isAlertMessageDisplayed());

        Thread.sleep(10000);
   }

}
