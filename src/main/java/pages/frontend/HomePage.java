package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }


    // =========================
    // Elements
    // =========================

    @FindBy (id = "tabs_invoices")
    WebElement invoiceButton;


    // =========================
    // Basic actions
    // =========================

    private boolean isInvoiceButtonVisible(){
        return super.isElementDisplayed(invoiceButton);
    }

    private void clickInvoiceButton(){
        super.click(invoiceButton);
    }


    // =========================
    // Business action
    // =========================

    public void openInvoicePage(){
        if (isInvoiceButtonVisible()){
            clickInvoiceButton();
        }
    }


    }
