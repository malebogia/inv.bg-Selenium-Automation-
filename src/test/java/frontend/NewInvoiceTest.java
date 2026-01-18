package frontend;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewInvoiceTest extends BaseTest {

    @Test
    public void createNewInvoice(){
        authService.loginToInvoicePage();
        webApp.invoicesListPage().clickNewInvoiceButtonn();
        webApp.newInvoicePage().tryToCreateNewInvoice("Kiril Kiril","12345678", "Toni Toni", "Sofia", "Mladost nomer 4",
                "Milen Milen", "19","20","Awesome Cotton Towels","Pamuk");
        Assert.assertTrue(webApp.newInvoicePage().isErrorNewInvoiceMessageDisplayed());

    }

}
