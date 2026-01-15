package frontend;

import base.BaseTest;
import enums.InvoiceSearchType;
import enums.Language;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.frontend.InvoicesListPage;

public class InvoicesPageTest extends BaseTest {


    @Test
    public void deleteInvoice() {
        authService.defaultLogin();
        webApp.homePage().openInvoicePage();
        webApp.invoicesListPage().deleteInvoice("42");
        Assert.assertTrue(webApp.invoicesListPage().isInvoiceDeleted("43"),
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
    public void searchInvoiceTest(InvoiceSearchType type, String value, String expectedResult) {
        authService.defaultLogin();
        webApp.homePage().openInvoicePage();
        String actualResult = webApp.invoicesListPage().searchInvoice(type, value);
        Assert.assertEquals(actualResult, expectedResult);


    }

    @Test
    public void cancelInvoice(){
        authService.defaultLogin();
        webApp.homePage().openInvoicePage();
        webApp.invoicesListPage().cancelInvoice("38");
        Assert.assertTrue(webApp.invoicesListPage().isSuccessCancelInvoiceMsgDisplayed());
        Assert.assertTrue(webApp.invoicesListPage().isInvoiceAnnulled("38"));
    }

    @Test
   public void  removeCancellationOfInvoice(){
        authService.defaultLogin();
        webApp.homePage().openInvoicePage();
        webApp.invoicesListPage().removeInvoiceAnnulation("38");
        Assert.assertTrue(webApp.invoicesListPage().isSuccessCancelInvoiceMsgDisplayed());
        Assert.assertFalse(webApp.invoicesListPage().isInvoiceAnnulled("38"));
    }

    @Test
    public void selectAllInvoices(){
        authService.defaultLogin();
        webApp.homePage().openInvoicePage();
        Assert.assertTrue(
                webApp.invoicesListPage().areAllInvoicesSelected(),
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
        authService.defaultLogin();
        webApp.homePage().openInvoicePage();
        webApp.invoicesListPage().makePartiallyPaidInvoiceStatus("41");
        Assert.assertTrue(webApp.invoicesListPage().isInvoicePartiallyPaid("41"));

    }

    @Test
    public void makeInvoicePaid(){
        authService.defaultLogin();
        webApp.homePage().openInvoicePage();
        webApp.invoicesListPage().makeInvoicePaid("41");
        Assert.assertTrue(webApp.invoicesListPage().isInvoicePaid("41"));
    }

    @Test
    public void makeInvoiceStatusDraft(){
        authService.defaultLogin();
        webApp.homePage().openInvoicePage();
        webApp.invoicesListPage().makeInvoiceStatusDraft("41");
        Assert.assertTrue(webApp.invoicesListPage().isInvoiceDraft("41"));

    }

    @Test
    public void login(){
        authService.defaultLogin();
    }





}

