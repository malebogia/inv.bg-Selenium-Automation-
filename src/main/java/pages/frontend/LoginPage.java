package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    public enum Language {
        BG,
        EN
    }

    private static final String  URL = "https://tester-123.inv.bg/login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // Elements
    // =========================

    @FindBy(id = "loginusername")
    WebElement emailFieldInput;

    @FindBy(id = "loginpassword")
    WebElement passwordField;

    @FindBy(id = "loginsubmit")
    WebElement loginButton;

    @FindBy(css = "input[value='BG']")
    WebElement radioButtonBg;

    @FindBy(css = "input[value='EN']")
    WebElement radioButtonEn;

    @FindBy(id = "error")
    WebElement errorMessage;

    @FindBy(id = "firstloginalert2")
    WebElement firstLoginAlert;

    // =========================
    // Basic actions
    // =========================

    public void openPage(){
        driver.get("https://tester-123.inv.bg/login");
    }

    private void enterEmail(String email) {
        super.typeText(emailFieldInput, email);
    }

    private void enterPassword(String password) {
        super.typeText(passwordField, password);
    }


    private void selectLanguage(Language language){
        if (language == Language.BG && !super.isElementSelected(radioButtonBg)){
            super.click(radioButtonBg);
        }
        if (language == Language.EN && !super.isElementSelected(radioButtonEn)){
            super.click(radioButtonEn);
        }

    }


    private void submitLogin() {
        super.click(loginButton);
    }

    // =========================
    // Business action
    // =========================

    public void login(String email, String password, Language language){
        if (email != null){
            enterEmail(email);
        }
        enterPassword(password);
        selectLanguage(language);
        submitLogin();

    }


    public void tryToLoginWithInvalidCredentials(String email , String password){
        if (email != null){
            enterEmail(email);
        }
        enterPassword(password);
        selectLanguage(Language.EN);
        submitLogin();

    }

    public boolean isErrorMessageDisplayed(){
       return super.isElementDisplayed(errorMessage);
    }

    public boolean isAlertMessageDisplayed(){
        return  super.isElementDisplayed(firstLoginAlert);
    }




}
