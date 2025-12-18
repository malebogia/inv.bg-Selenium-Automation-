package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class MainPage extends BasePage {


    public MainPage(WebDriver driver) {
        super(driver);
    }


        private static final String URL = "https://inv.bg/";

    // =========================
    // Elements
    // =========================


    @FindBy (css = "#inheader a.inheaderlogin")
    WebElement loginButton;

    @FindBy (id = "login-subdomain")
    WebElement emailInputField;

    @FindBy (id = "gologin")
    WebElement enterButton;

    @FindBy (id = "loginpassword")
    WebElement passwordField;

    @FindBy (id = "introbtnv2")
    WebElement registerButton;



    // =========================
    // Basic actions
    // =========================


    public void goToLoginPage() {
        driver.get(URL);
    }

    public void clickRegisterButton(){super.click(registerButton);}

    public void clickLoginButton() {
        super.click(loginButton);
    }

    public void enterEmail(String string) {
        super.typeText(emailInputField, string);
    }

    public void clickEnterButton(){
        super.click(enterButton);
    }

    public boolean isPasswordFieldDisplayed(){
       return passwordField.isDisplayed();

    }

    public  String getEnterPageUrl(){
        return super.getCurrentUrl();
    }

    // =========================
    // Business action
    // =========================


    public void login(String email){
        clickLoginButton();
        enterEmail(email);
        clickEnterButton();

    }


}

