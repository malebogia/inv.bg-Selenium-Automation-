package pages.frontend;

import enums.AddItemInputFieldIndex;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class NewInvoicePage extends BasePage {

    public NewInvoicePage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // Elements
    // =========================

    @FindBy(id = "invtype_dan")
    WebElement invoiceRadioButton;

    @FindBy(id = "client_firmname")
    WebElement billToInputField;

    @FindBy(id = "clbulstat")
    WebElement companyNumberInputField;

    @FindBy(id = "client-firm-mol")
    WebElement accountablePersonInputField;

    @FindBy(id = "firm_city")
    WebElement cityInputField;

    @FindBy(id = "firm_addr")
    WebElement addressInputField;

    @FindBy(id = "client-recipient")
    WebElement recipientInputField;

    @FindBy(id = "dt1")
    WebElement dateOfIssueButton;

    @FindBy(id = "ui-datepicker-div")
    WebElement dateOfIssueCalendar;

    @FindBy(id = "dt2")
    WebElement timeOfSupplyButton;

    @FindBy(id = "invoice-item-1-name")
    WebElement addItemInput;

    @FindBy (css = "tr#obj_1 a[title='Add a new row above']")
    WebElement addNewItemButton;

    @FindBy(id = "payment_type")
    WebElement methodOfPaymentDropDown;

    @FindBy(id = "act_create")
    WebElement createInvoiceButton;

    @FindBy (xpath = "//div[contains(@class,'selenium-error-block')]     " +
            "[contains(normalize-space(.),'you have already reached the limit for the number of clients')]")
    WebElement errorNewInvoiceMessage;


    // =========================
    // LOCATORS (DYNAMIC)
    // =========================

    private By savedItemsInputLocator(AddItemInputFieldIndex field) {

        return By.cssSelector("a.selenium-open-item-selector-modal-" + field.getIndex());

    }

    private By dateLocator(String date) {
        return By.xpath("//div[@id='ui-datepicker-div']//a[normalize-space()='" + date + "']");

    }

    private By savedInvoiceItemLocator(String savedItem){
        return By.xpath("//div[@id='flylistContainer']//span[normalize-space(text())='" + savedItem + "']");
    }


    // =========================
    // Basic actions
    // =========================

    private void typeBillTo(String typeTo) {
        super.typeText(billToInputField, typeTo);
    }

    private void typeCompanyNumber(String companyNumber) {
        super.typeText(companyNumberInputField, companyNumber);

    }


    private void typeAccountablePerson(String accountablePerson) {
        super.typeText(accountablePersonInputField, accountablePerson);
    }

    private void typeCity(String city) {
        super.typeText(cityInputField, city);
    }

    private void typeAddress(String address) {
        super.typeText(addressInputField, address);

    }

    private void typeRecipient(String recipient) {
        super.typeText(recipientInputField, recipient);

    }

    private void chooseDateOfIssue(String date) {
        super.click(dateOfIssueButton);
        super.waitForElementByLocator(dateLocator(date)).click();

    }

    private void chooseTimeOfSupply(String date) {
        super.click(timeOfSupplyButton);
        super.waitForElementByLocator(dateLocator(date)).click();

    }

    private void addItemIntoCatalog(AddItemInputFieldIndex field, String item) {
        WebElement itemField = super.waitForElementByLocator(savedItemsInputLocator(field));
        super.typeText(itemField, item);

    }

    public boolean isErrorNewInvoiceMessageDisplayed(){
       return super.isElementDisplayed(errorNewInvoiceMessage);
    }

    // =========================
    // Business action
    // =========================


    private void chooseDateOfIssueAndDateOfSupply(String dateOfIssue, String dateOfSupply) {
        chooseDateOfIssue(dateOfIssue);
        chooseTimeOfSupply(dateOfSupply);

    }


    /*Trying to create a new invoice after reaching the limit of
    created clients according to the tarrif plan*/
    public void tryToCreateNewInvoice(String billTo, String companyNumber, String accPerson, String city,
                                 String address, String recipient, String dateOfIssue, String dateOfSupply,String savedItem, String item
    ) {
        if (!super.isElementSelected(invoiceRadioButton)) {
            super.click(invoiceRadioButton);
        }
        super.typeText(billToInputField, billTo);
        super.typeText(companyNumberInputField, companyNumber);
        super.typeText(accountablePersonInputField, accPerson);
        super.typeText(cityInputField, city);
        super.typeText(addressInputField, address);
        super.typeText(recipientInputField, recipient);
        chooseDateOfIssueAndDateOfSupply(dateOfIssue, dateOfSupply);
        super.waitForElementByLocator(savedItemsInputLocator(AddItemInputFieldIndex.FIRST_FIELD)).click();
        super.waitForElementByLocator(savedInvoiceItemLocator(savedItem)).click();
        super.typeText(addItemInput, item);
      //  super.click(addNewItemButton);
        super.selectByValue(methodOfPaymentDropDown,"cash");
        super.click(createInvoiceButton);

    }
    


}


