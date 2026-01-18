package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.base.WebApp;

public class InvoiceCreatedConfirmPage extends BasePage {
    public InvoiceCreatedConfirmPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // LOCATORS
    // =========================

    @FindBy (xpath = "//tr[@data-id='103']//span[contains(text(), 'created')]")
    WebElement invoiceCreateMessage;


    // =========================
    // Basic actions
    // =========================

    public boolean isInvoiceCreated(){
        return super.isElementDisplayed(invoiceCreateMessage);
    }


}
