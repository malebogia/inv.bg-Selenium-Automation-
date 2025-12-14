package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class EnterPage extends BasePage {

    public enum Language {
        BG,
        EN
    }

    public EnterPage(WebDriver driver) {
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

    // =========================
    // Basic actions
    // =========================

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




}
