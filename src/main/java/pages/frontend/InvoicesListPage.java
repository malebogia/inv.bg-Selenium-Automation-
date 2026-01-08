package pages.frontend;

import enums.InvoiceSearchType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class InvoicesListPage extends BasePage {


    // =========================
    // Elements
    // =========================

    @FindBy(id = "userpanel")
    private WebElement userPanel;

    @FindBy(css = "a.icon.delete")
    private WebElement deleteButton;

    @FindBy(css = "div.modal-inner button.modal-confirm__ok-button")
    private WebElement deleteConfirmButton;

    @FindBy(css = "div.modal-inner button.modal-confirm__cancel-button")
    private WebElement cancelDeleteButton;

    @FindBy(css = "a.toggle-filter")
    private WebElement searchButton;

    @FindBy(id = "invnmb")
    private WebElement searchInvoiceNumberInput;

    @FindBy(id = "client_name")
    private WebElement searchClientNameInput;

    @FindBy(id = "client")
    private WebElement searchCompanyNumberInput;

    @FindBy(id = "client-recipient")
    private WebElement searchRecipientInput;

    @FindBy(css = "button[name='FilterForm']")
    private WebElement searchButtonSubmit;

    @FindBy(id = "total-invoices-found")
    private WebElement foundInvoiceMessage;

    @FindBy(css = "a.selenium-client-link")
    private WebElement invoiceSearchResultName;


    public InvoicesListPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // LOCATORS (DYNAMIC)
    // =========================

    private By getCheckboxLocatorByValue(String value) {
        return By.cssSelector("input[type='checkbox'][value='" + value + "']");
    }

    private By invoiceSearchResultNameLocator =
            By.cssSelector("a.selenium-client-link");

    // =========================
    // Basic actions
    // =========================


    public boolean isUserPanelDisplayed() {
        return super.isElementDisplayed(userPanel);
    }

    private WebElement getCheckboxByValue(String value) {
        By checkboxLocator = getCheckboxLocatorByValue(value);
        if (super.isElementNotPresent(checkboxLocator)) {
            throw new AssertionError("Invoice with number " + value + " was not found");
        }
        return super.waitForElementByLocator(checkboxLocator);
    }


    // =========================
    // Business action
    // =========================

    public void selectCheckBoxByValue(String value) {
        WebElement checkbox = getCheckboxByValue(value);
        if (!super.isElementSelected(checkbox)) {
            super.click(checkbox);
        }
    }

    public void deleteInvoice(String value) {
        selectCheckBoxByValue(value);
        super.click(deleteButton);
        super.click(deleteConfirmButton);
        super.waitForElementDisappear(getCheckboxLocatorByValue(value));
    }

    public boolean isInvoiceDeleted(String invoiceValue) {
        By invoiceLocator = getCheckboxLocatorByValue(invoiceValue);
        return !super.isElementPresent(invoiceLocator);
    }


    private void clickSearchButton() {
        super.click(searchButton);
    }

    private void clickSearchButtonSubmit() {
        super.click(searchButtonSubmit);
    }


    public WebElement invoiceNumberInput() {
        return searchInvoiceNumberInput;
    }

    public WebElement sentToClientNameInput() {
        return searchClientNameInput;
    }

    public WebElement companyNumberInput() {
        return searchCompanyNumberInput;
    }

    public WebElement recipientNameInput() {
        return searchRecipientInput;
    }


    public String searchInvoice(InvoiceSearchType type, String value) {

        clickSearchButton();
        WebElement inputField = resolveSearchField(type);

        typeText(inputField, value);
        clickSearchButtonSubmit();
        WebElement result =
                super.waitForElementByLocator(invoiceSearchResultNameLocator);

        return result.getText();


    }



    // =========================
    // Internal helper
    // =========================


    private WebElement resolveSearchField(InvoiceSearchType type) {
        switch (type) {
            case INVOICE_NUMBER:
                return searchInvoiceNumberInput;
            case CLIENT_NAME:
                return searchClientNameInput;
            case COMPANY_NUMBER:
                return searchCompanyNumberInput;
            case RECIPIENT:
                return searchRecipientInput;
            default:
                throw new IllegalArgumentException("Please enter only supported value");
        }

    }

}


