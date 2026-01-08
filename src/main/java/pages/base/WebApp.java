package pages.base;

import org.openqa.selenium.WebDriver;
import pages.frontend.*;

public class WebApp {

    private WebDriver driver;
    private MainPage loginPage;
    private LoginPage enterPage;
    private InvoicesListPage invoicesListPage;
    private RegisterPage registerPage;
    private RegistrationConfirmPage regConfirmPage;
    private HomePage homePage;


    public WebApp(WebDriver driver){
        this.driver = driver;
    }

    public MainPage mainPage(){
        if (loginPage == null){
            loginPage = new MainPage(driver);
        }
        return loginPage;
    }

    public LoginPage loginPage(){
        if (enterPage == null){
            enterPage = new LoginPage(driver);
        }
        return enterPage;
    }

    public InvoicesListPage invoicesListPage(){
        if (invoicesListPage == null){
            invoicesListPage = new InvoicesListPage(driver);
        }
        return invoicesListPage;
    }

    public RegisterPage registerPage(){
        if (registerPage == null){
            registerPage = new RegisterPage(driver);
        }
        return registerPage;
    }

    public RegistrationConfirmPage registrationConfirmPagePage(){
        if (regConfirmPage == null){
            regConfirmPage = new RegistrationConfirmPage(driver);
        }
        return regConfirmPage;
    }

    public HomePage homePage(){
        if (homePage == null){
            homePage = new HomePage(driver);
        }
        return  homePage;
    }






}
