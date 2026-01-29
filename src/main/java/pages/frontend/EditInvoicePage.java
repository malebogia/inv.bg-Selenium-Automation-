package pages.frontend;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.base.WebApp;

public class EditInvoicePage extends BasePage {

    public EditInvoicePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "client_firmname")
    WebElement billToInput;

    @FindBy (id = "clbulstat")
    WebElement companyNumberInput;

    @FindBy (id = "invoice-item-0-quantity")
    WebElement quantityOfItem;
}

    
