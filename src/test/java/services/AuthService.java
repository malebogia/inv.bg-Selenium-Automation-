package services;

import enums.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pages.base.WebApp;

public class AuthService {

    private WebApp webApp;
    private final String DEFAULT_VALID_EMAIL = "malebogia91@gmail.com";
    private final String DEFAULT_VALID_PASSWORD = "12345678";

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(WebApp webApp) {
        this.webApp = webApp;
    }

    private void login(String email, String password, Language language) {
        logger.info("STEP: Login started for email [{}]", email);
        webApp.mainPage().openLoginPage();
        webApp.mainPage().startLogin(email);
        webApp.loginPage().login(email, password, language);
        logger.info("STEP: Login successful for email [{}]", email);
    }

    public void defaultLogin() {
        logger.info("Executing default login");
        login(DEFAULT_VALID_EMAIL, DEFAULT_VALID_PASSWORD, Language.EN);
    }

    public void loginToInvoicePage() {
        logger.info("STEP: Login to Invoice Page started");
        defaultLogin();
        if (!webApp.invoicesListPage().isInvoicePageOpened()) {
            logger.info("STEP: Invoice page not opened, navigating manually");
            webApp.homePage().openInvoicePage();
        }
        logger.info("Invoice page opened successfully");
    }
}
