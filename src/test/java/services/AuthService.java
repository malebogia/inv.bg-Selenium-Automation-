package services;

import org.openqa.selenium.WebDriver;
import pages.base.WebApp;
import pages.frontend.LoginPage;

public class AuthService {

    private final WebDriver driver;
    private final WebApp webApp;


    public AuthService(WebDriver driver,WebApp webApp) {
        this.driver = driver;
        this.webApp = webApp;
    }

    public void loginAsUser(String email, String password, LoginPage.Language language){
        driver.get("\"https://tester-123.inv.bg/login\"");
        webApp.loginPage().login(email,password,language );
    }
}
