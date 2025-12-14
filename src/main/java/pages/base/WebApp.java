package pages.base;

import org.openqa.selenium.WebDriver;
import pages.frontend.EnterPage;
import pages.frontend.InvoicesListPage;
import pages.frontend.LoginPage;

public class WebApp {

    private WebDriver driver;
    private LoginPage loginPage;
    private EnterPage enterPage;
    private InvoicesListPage invoicesListPage;


    public WebApp(WebDriver driver){
        this.driver = driver;
    }

    public LoginPage loginPage(){
        if (loginPage == null){
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public EnterPage enterPage(){
        if (enterPage == null){
            enterPage = new EnterPage(driver);
        }
        return enterPage;
    }

    public InvoicesListPage invoicesListPage(){
        if (invoicesListPage == null){
            invoicesListPage = new InvoicesListPage(driver);
        }
        return invoicesListPage;
    }




}
