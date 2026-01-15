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

    @FindBy(id= "invtype_dan")
    WebElement invoiceRadioButton;

    @FindBy (id = "client_firmname")
    WebElement billToInputField;

    @FindBy (id = "clbulstat")
    WebElement companyNumberInputField;

    @FindBy (id = "client-firm-mol")
    WebElement accountablePersonInputField;

    @FindBy (id = "firm_city")
    WebElement cityInputField;

    @FindBy (id = "firm_addr")
    WebElement addressInputField;

    @FindBy (id = "client-recipient")
    WebElement recipientInputField;

    @FindBy (id = "dt1")
    WebElement dateOfIssueButton;

    @FindBy (id = "ui-datepicker-div")
    WebElement dateOfIssueCalendar;

    @FindBy (id = "dt2")
    WebElement timeOfSupplyButton;



    // =========================
    // LOCATORS (DYNAMIC)
    // =========================

    private By addItemInputLocator(AddItemInputFieldIndex field){

      return By.cssSelector("a.selenium-open-item-selector-modal-" + field.getIndex());

    }

    private By dateLocator(String date){
        return By.xpath("//div[@id='ui-datepicker-div']//a[normalize-space()='" + date + "']");

    }




    // =========================
    // Basic actions
    // =========================

    private void typeBillTo(String typeTo){
        super.typeText(billToInputField,typeTo);
    }

    private  void typeCompanyNumber(String companyNumber){
        super.typeText(companyNumberInputField,companyNumber);

    }


    private  void typeAccountablePerson(String accountablePerson){
        super.typeText(accountablePersonInputField,accountablePerson);
    }

    private  void typeCity(String city){
        super.typeText(cityInputField,city);
    }

    private  void typeAddress(String address){
        super.typeText(addressInputField,address);

    }

    private void typeRecipient(String recipient){
        super.typeText(recipientInputField,recipient);

    }

    private void chooseDateOfIssue(String date){
        super.click(dateOfIssueButton);
        super.waitForElementByLocator(dateLocator(date)).click();

    }

    private void chooseTimeOfSupply(String date){
        super.click(timeOfSupplyButton);
        super.waitForElementByLocator(dateLocator(date)).click();

    }

    private void addItemIntoCatalog(AddItemInputFieldIndex field, String item){
        WebElement itemField = super.waitForElementByLocator(addItemInputLocator(field));
        super.typeText(itemField,item);

    }

    // =========================
    // Business action
    // =========================

    public void chooseDateOfIssueAndDateOfSupply(String dateOfIssue, String dateOfSupply){
        chooseDateOfIssue(dateOfIssue);
        chooseTimeOfSupply(dateOfSupply);

    }







}


