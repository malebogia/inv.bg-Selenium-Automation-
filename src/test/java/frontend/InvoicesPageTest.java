package frontend;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.base.WebApp;
import pages.frontend.InvoicesListPage;

public class InvoicesPageTest extends BaseTest {

    /**
     * For this class, use the helper method `super.loginEN(String email, String password)`
     * or `super.loginBG(String email, String password)`, depending on the required language.
     * These methods are in `BaseTest` because the page under test requires login.
     */


    @Test
    public void deleteInvoice() {
        super.loginInEn("malebogia91@gmail.com", "12345678");
        webApp.invoicesListPage().openPage();
        webApp.invoicesListPage().deleteInvoice("40");
        Assert.assertTrue(webApp.invoicesListPage().isInvoiceDeleted("40"),
                "Invoice was NOT deleted"
        );

    }

    @Test
    public void probaSearch(){
        super.loginInEn("malebogia91@gmail.com", "12345678");
        webApp.invoicesListPage().openPage();
        webApp.invoicesListPage().clickSearchButton();
        Assert.assertTrue(webApp.invoicesListPage().isInvoiceNumberInputDisplayed());
    }




}
