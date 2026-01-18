package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.base.WebApp;

import java.util.Objects;

public class HomePage extends BasePage {

    private String url = "https://tester-123.inv.bg/home";

    public HomePage(WebDriver driver) {
        super(driver);
    }


    // =========================
    // Elements
    // =========================

    @FindBy (id = "tabs_invoices")
    WebElement invoiceButton;

    @FindBy (id = "tabs_invoices/new")
    WebElement newInvoiceButton;

    @FindBy (id = "div#home_stats")
    WebElement homePageStats;


    // =========================
    // Basic actions
    // =========================

    private boolean isInvoiceButtonVisible(){
        return super.isElementDisplayed(invoiceButton);
    }

    private void clickInvoiceButton(){
        super.click(invoiceButton);
    }

    private boolean isNewInvoiceButtonVisible(){
        return super.isElementDisplayed((newInvoiceButton));
    }

    private void clickNewInvoiceButton(){
        super.click(newInvoiceButton);
    }


    // =========================
    // Business action
    // =========================

   public boolean isHomePageOpened(){
       return super.isElementDisplayed(homePageStats);
    }

    public void openInvoicePage(){
        if (isInvoiceButtonVisible()){
            clickInvoiceButton();
        }
    }

    public void openNewInvoicePage(){
        if (isNewInvoiceButtonVisible()){
            clickNewInvoiceButton();
        }
    }


    }
