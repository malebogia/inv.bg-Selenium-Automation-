package pages.frontend;

import enums.InvoiceSearchType;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;

import java.util.List;

public class InvoicesListPage extends BasePage {

    protected static final Logger logger =
            LoggerFactory.getLogger(InvoicesListPage.class);


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

    @FindBy(css = ".anull-deanull")
    private WebElement cancelInvoiceButton;

    @FindBy(css = ".anull-deanull-drop-down .anull")
    private WebElement cancelInvoiceDropDownButton;

    @FindBy(css = "button.modal-confirm__ok-button")
    private WebElement modalConfirmButton;

    @FindBy(css = ".deanull[title = 'Remove cancellation']")
    private WebElement removeCancellationButton;

    @FindBy(id = "okmsg")
    private WebElement cancellationOkMessage;

    @FindBy(css = "input[name='chk-all']")
    private WebElement checkAllBoxInvoices;

    @FindBy(css = "a[title='Mark as']")
    private WebElement markAsButton;

    @FindBy(css = "div .partially-paid")
    private WebElement partiallyPaidButton;

    @FindBy(css = "div .paid")
    private WebElement paidButton;

    @FindBy(css = "div .draft")
    private WebElement draftButton;

    @FindBy(css = "div .accounted")
    private WebElement postedButton;

    @FindBy(css = "div .archive")
    private WebElement archiveButton;

    @FindBy(css = "div[rel='unpaid']")
    private WebElement unPaidButton;

    @FindBy(css = "div .modal-confirm__buttons button.modal-confirm__ok-button")
    WebElement confirmInvoiceStatusButton;

    @FindBy(css = "a.new")
    WebElement newInvoiceButton;




    public InvoicesListPage(WebDriver driver) {
        super(driver);
    }


    // =========================
    // LOCATORS (DYNAMIC)
    // =========================

    private By getCheckboxLocatorByValue(String value) {
        return By.xpath("//a[contains(text(), '" + value + "')]/ancestor::tr//input[@type='checkbox']");
    }

    private By invoiceSearchResultNameLocator =
            By.cssSelector("a.selenium-client-link");

    private By getAnnulledInvoiceLocator(String invoiceNumber) {
        return By.cssSelector(
                "tr[data-invoice-id='" + invoiceNumber + "'] span.anulled"
        );
    }

    private By getAllInvoiceCheckBoxes =
            By.cssSelector("input[type='checkbox'][name='invoices[]']");

    private By invoicePaymentStatusLocator(String invoiceNumber) {
        return By.xpath("//a[contains(text(), '" + invoiceNumber + "')]/ancestor::tr//div[contains(@class, 'pstatus')]");
    }

    private By draftStatusLocator(String invoiceNumber){
            return By.xpath(
                    "//a[contains(text(), '" + invoiceNumber + "')]/ancestor::tr//span[contains(@class,'draft')]"
            );
    }


    // =========================
    // Basic actions
    // =========================

    public boolean isInvoicePageOpened() {
        return super.isElementDisplayed(cancelInvoiceButton);
    }


    public boolean isUserPanelDisplayed() {
        return super.isElementDisplayed(userPanel);
    }

    public boolean isSuccessCancelInvoiceMessageDisplayed() {
        return super.isElementDisplayed(cancellationOkMessage);
    }

    private WebElement getCheckboxByValue(String value) {
        By checkboxLocator = getCheckboxLocatorByValue(value);
        try {
            return super.waitForElementByLocator(checkboxLocator);
        } catch (TimeoutException e) {
            throw new AssertionError("Functional Failure: Invoice #" + value +
                    " was not found within the timeout period");
        }
    }


    public void clickNewInvoiceButton() {
        super.click(newInvoiceButton);
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


    private WebElement invoiceNumberInput() {
        return searchInvoiceNumberInput;
    }

    private WebElement sentToClientNameInput() {
        return searchClientNameInput;
    }

    private WebElement companyNumberInput() {
        return searchCompanyNumberInput;
    }

    private WebElement recipientNameInput() {
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


    public void cancelInvoice(String invoiceNumber) {
        selectCheckBoxByValue(invoiceNumber);
        super.click(cancelInvoiceButton);
        super.click(cancelInvoiceDropDownButton);
        super.click(modalConfirmButton);


    }

    public void removeInvoiceAnnulation(String invoiceNumber) {
        if (!isInvoiceAnnulled(invoiceNumber)) {
            throw new IllegalArgumentException(
                    "Invoice " + invoiceNumber + " is not annulled"
            );

        }
        selectCheckBoxByValue(invoiceNumber);
        super.click(cancelInvoiceButton);
        super.click(removeCancellationButton);
        super.click(modalConfirmButton);

    }

    public boolean isInvoiceAnnulled(String invoiceNumber) {
        try {
            super.waitForElementByLocator(getAnnulledInvoiceLocator(invoiceNumber));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void selectAllCheckboxesAtOnce() {
        super.click(checkAllBoxInvoices);
    }

    public boolean isAllCheckBoxesSelected() {
        List<WebElement> checkBoxes =
                super.getElements(getAllInvoiceCheckBoxes);
        for (WebElement checkbox : checkBoxes) {
            if (!checkbox.isSelected()) {
                return false;
            }
        }
        return true;
    }

    public void makeInvoiceStatusDraft(String invoiceNumber){
        if (isInvoiceDraft(invoiceNumber)) {
            logger.warn("Invoice [{}], is already in DRAFT status",invoiceNumber);
            return;
        }
        selectCheckBoxByValue(invoiceNumber);
        super.click(markAsButton);
        super.click(draftButton);
        super.click(confirmInvoiceStatusButton);

    }


    public boolean isInvoiceDraft(String invoiceNumber){
      try{
          WebElement status =
                  super.waitForElementByLocator(draftStatusLocator(invoiceNumber));
          return status.getText().trim().equalsIgnoreCase("draft");
      } catch (TimeoutException e) {
          return false;
      }
    }


    public void makePartiallyPaidInvoiceStatus(String invoiceNumber) {
        selectCheckBoxByValue(invoiceNumber);
        super.click(markAsButton);
        super.click(partiallyPaidButton);
        super.click(confirmInvoiceStatusButton);
    }

    public boolean isInvoicePartiallyPaid(String invoiceNumber) {
       return super.waitForElementByLocator(invoicePaymentStatusLocator(invoiceNumber)).
                getText().
                trim().
                equalsIgnoreCase("Partially paid");

    }

    public void makeInvoicePaid(String invoiceNumber) {
        if (isInvoicePaid(invoiceNumber)) {
            throw new IllegalStateException("Invoice " + invoiceNumber + " is already marked as Paid");
        }
        selectCheckBoxByValue(invoiceNumber);
        super.click(markAsButton);
        super.click(paidButton);
        super.click(confirmInvoiceStatusButton);
    }

    public boolean isInvoicePaid(String invoiceNumber) {
        WebElement invoiceStatus =
                super.waitForElementByLocator(invoicePaymentStatusLocator(invoiceNumber));
        return invoiceStatus
                .getText()
                .trim()
                .equalsIgnoreCase("Paid");
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




























