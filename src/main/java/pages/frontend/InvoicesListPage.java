package pages.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import utils.WaitTool;

public class InvoicesListPage extends BasePage {

    private static final String URL = "https://tester-123.inv.bg/invoices";

    // =========================
    // Elements
    // =========================

    @FindBy(id = "userpanel")
    private WebElement userPanel;

    @FindBy(css = "a.icon.delete")
    WebElement deleteButton;

    @FindBy(css = "div.modal-inner button.modal-confirm__ok-button")
    WebElement deleteConfirmButton;

    @FindBy(css = "div.modal-inner button.modal-confirm__cancel-button")
    WebElement cancelDeleteButton;


    public InvoicesListPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // Basic actions
    // =========================


    private WebElement getCheckboxByValue(String value) {
       By checkboxLocator = By.cssSelector("input[type='checkbox'][value='" + value + "']");
       return super.waitForElementByLocator(checkboxLocator);
    }


    public boolean isUserPanelDisplayed() {
        return super.isElementDisplayed(userPanel);
    }

    public void openPage() {
        driver.get(URL);
    }

    // =========================
    // Business action
    // =========================

    public void selectCheckBoxByValue(String value) {
        WebElement checkbox = getCheckboxByValue(value);
        if (!super.isElementSelected(checkbox)){
            super.click(checkbox);
        }
    }





}


