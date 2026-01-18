package services;

import enums.Language;
import pages.base.WebApp;

public class AuthService {

    private WebApp webApp;
    private final String DEFAULT_VALID_EMAIL = "malebogia91@gmail.com";
    private final String DEFAULT_VALID_PASSWORD = "12345678";

    public AuthService(WebApp webApp) {
        this.webApp = webApp;
    }

    private void login(String email, String password, Language language) {
        webApp.mainPage().openLoginPage();
        webApp.mainPage().startLogin(email);
        webApp.loginPage().login(email, password, language);

    }

    public void defaultLogin() {
        login(DEFAULT_VALID_EMAIL, DEFAULT_VALID_PASSWORD, Language.EN);
    }


    public void loginToInvoicePage() {
        defaultLogin();
        if (!webApp.invoicesListPage().isInvoicePageOpened()) {
            webApp.homePage().openInvoicePage();
        }


    }

}


