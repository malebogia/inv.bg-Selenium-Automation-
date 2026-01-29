package frontend;

import annotations.TestId;
import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewInvoiceTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(NewInvoiceTest.class);

    @Test
    @TestId("TC-004")
    public void createNewInvoice(){
        authService.loginToInvoicePage();
        logger.info("Transition from the Invoice page to the New Invoice page ");
        webApp.invoicesListPage().clickNewInvoiceButton();

            logger.info("[{}] Attempting to create a new invoice after reaching client limit", MDC.get("testId"));
        webApp.newInvoicePage().tryToCreateNewInvoice("Kiril Kiril","12345678", "Toni Toni", "Sofia", "Mladost nomer 4",
                "Milen Milen", "19","20","Awesome Cotton Towels","Pamuk");

        Assert.assertTrue(webApp.newInvoicePage().isErrorNewInvoiceMessageDisplayed());

    }

}
