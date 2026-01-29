package frontend;

import annotations.TestId;
import base.BaseTest;
import enums.InvoiceSearchType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InvoicesPageTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(InvoicesPageTest.class);


    @Test
    @TestId("TC-004")
    public void deleteInvoice() {
        logger.info("Test Scenario: Delete an invoice and verify it is removed");

        authService.defaultLogin();

        logger.info("Navigation: Opening Invoices page from Home page");
        webApp.homePage().openInvoicePage();

        String invoiceNumber = "42";

        logger.info("Test Scenario: Delete an invoice and verify it is removed");

        logger.info("Action: Deleting invoice [{}]", invoiceNumber);
        webApp.invoicesListPage().deleteInvoice(invoiceNumber);


        Assert.assertTrue(webApp.invoicesListPage().isInvoiceDeleted(invoiceNumber),
                "Invoice was NOT deleted"
        );

    }

    @DataProvider
    public Object[][] invoiceSearchData() {
        return new Object[][]{
                {InvoiceSearchType.INVOICE_NUMBER, "0000000041", "Caleb Kerluke"},
                {InvoiceSearchType.CLIENT_NAME, "Caleb Kerluke", "Caleb Kerluke"},
                {InvoiceSearchType.COMPANY_NUMBER, "BG801", "Caleb Kerluke"},
                {InvoiceSearchType.RECIPIENT, "Teri Dickinson", "Caleb Kerluke"}
        };
    }

    @Test(dataProvider = "invoiceSearchData")
    @TestId("TC-005")
    public void searchInvoiceTest(InvoiceSearchType type, String value, String expectedResult) {
        logger.info(
                "Test Scenario: Search invoice by [{}] using value [{}] and verify result",
                type, value
        );

        authService.defaultLogin();

       logger.info("Navigation: Opening Invoices page from Home page");
        webApp.homePage().openInvoicePage();

        logger.info("Action: Searching invoice by [{}] with value [{}]", type, value);
        String actualResult = webApp.invoicesListPage().searchInvoice(type, value);

        Assert.assertEquals(actualResult, expectedResult);


    }

    @Test
    public void cancelInvoice(){
        String invoiceNumber = "38";
        logger.info("TEST: Cancel (Annul) invoice by using value [{}]",invoiceNumber );

        authService.defaultLogin();

        logger.info("STEP: Opening Invoices page from Home page.");
        webApp.homePage().openInvoicePage();

        logger.info("STEP: Cancel invoice [{}]", invoiceNumber);
        webApp.invoicesListPage().cancelInvoice(invoiceNumber);
        Assert.assertTrue( webApp.invoicesListPage().isSuccessCancelInvoiceMessageDisplayed(),
                "Success message for invoice cancellation was NOT displayed" );

        Assert.assertTrue(webApp.invoicesListPage().isInvoiceAnnulled(invoiceNumber),
                "Invoice was NOT annulled: " + invoiceNumber);
    }

    @Test
   public void  removeCancellationOfInvoice(){
        String invoiceNumber = "38";
        logger.info("TEST: Remove cancellation of invoice by using invoice number [{}]", invoiceNumber);

        authService.defaultLogin();

        logger.info("STEP: Opening Invoices page from Home page.");
        webApp.homePage().openInvoicePage();

        logger.info("STEP: Remove cancellation");
        webApp.invoicesListPage().removeInvoiceAnnulation(invoiceNumber);

        Assert.assertTrue(webApp.invoicesListPage().isSuccessCancelInvoiceMessageDisplayed(),
                "Success message for removing invoice cancellation is not displayed");
        Assert.assertFalse(webApp.invoicesListPage().isInvoiceAnnulled(invoiceNumber),
                "Invoice cancellation was not removed");
    }

    @Test
    public void selectAllInvoices(){
        logger.info("TEST: Verify that the 'Select All' checkbox selects every invoice in the list." );

        authService.loginToInvoicePage();

        logger.info("STEP: Enable the 'Select All' master checkbox.");
        webApp.invoicesListPage().selectAllCheckboxesAtOnce();

        Assert.assertTrue(
                webApp.invoicesListPage().isAllCheckBoxesSelected(),
                "Not all invoice checkboxes are selected"

        );
    }

/*
 * This test fails consistently due to a known system issue.
 * When the same scenario is executed manually, the invoice status
 * is successfully changed to "Partially paid".
 *
 * During automated execution, the application displays the following error:
 * "There was a problem updating the status.
 * Please try again later or contact Technical Support.
 * We apologize for the inconvenience caused."
 *
 * The failure is not related to the test logic.
 */

    @Test
    public void makeInvoicePartiallyPaid(){
        String invoiceNumber = "43";

        logger.info( "TEST: Verify 'Partially Paid' status transition for Invoice #{}", invoiceNumber);

        authService.loginToInvoicePage();

        logger.info("STEP: Apply 'Partially Paid' status to Invoice #{}", invoiceNumber);
        webApp.invoicesListPage().makePartiallyPaidInvoiceStatus(invoiceNumber);

        Assert.assertTrue(webApp.invoicesListPage().isInvoicePartiallyPaid(invoiceNumber) ,
                "Assertion Failed: Invoice #" + invoiceNumber + " was not updated to 'Partially Paid' status.");


    }

    @Test
    public void makeInvoicePaid() throws InterruptedException {
        String invoiceNumber = "43";

        logger.info("TEST: Verify 'Paid' status transition for invoice #{}", invoiceNumber);

        authService.loginToInvoicePage();

        logger.info("STEP: Apply 'Paid' status to invoice #{}", invoiceNumber);
        webApp.invoicesListPage().makeInvoicePaid(invoiceNumber);



        Assert.assertTrue(webApp.invoicesListPage().isInvoicePaid(invoiceNumber),
                "Assertion failed: Invoice #" + invoiceNumber + "was not updated to 'Paid' status");
    }

    @Test
    public void makeInvoiceStatusDraft(){
        String invoiceNumber = "43";
        logger.info("TEST: Verify 'Draft' status transition for invoice #{}", invoiceNumber);

        authService.loginToInvoicePage();

        logger.info("STEP: Apply 'Draft' status to invoice #{}",invoiceNumber);
        webApp.invoicesListPage().makeInvoiceStatusDraft(invoiceNumber);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(webApp.invoicesListPage().isInvoiceDraft(invoiceNumber),
        "Assertion failed: Invoice #" + invoiceNumber + "was not updated to 'Draft' status" );

    }


  /*  @Test public void checkSingleMethod(){

    }

*/





}

