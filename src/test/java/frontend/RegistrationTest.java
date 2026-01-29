package frontend;

import annotations.TestId;
import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationTest.class);
    @Test
    @TestId("TC-001")
    public void registerNewAccount(){
        logger.info("Opening the Home page as a guest user.");
        webApp.mainPage().openLoginPage();

        logger.info("Navigating from Home Page to Registration Page");
        webApp.mainPage().clickRegisterButton();

        logger.info("Submitting registration form with valid user data");
        webApp.registerPage().registerNewAccount("neshto@gmail.com","12345678","pishmantester","companyDomain");

        Assert.assertTrue(webApp.registrationConfirmPagePage().isResendConfirmMessageButtonDisplayed());
    }
}
