package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.base.WebApp;

public class RegistrationConfirmPage extends BasePage {

    public RegistrationConfirmPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // Elements
    // =========================

    @FindBy (id = "selenium-resend-activation-email")
    WebElement resendConfirmMessageButton;

    // =========================
    // Business action
    // =========================

    public boolean isResendConfirmMessageButtonDisplayed(){
       return super.isElementDisplayed(resendConfirmMessageButton);
    }



}


