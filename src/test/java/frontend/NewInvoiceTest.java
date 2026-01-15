package frontend;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.base.WebApp;

public class NewInvoiceTest extends BaseTest {

    @Test
    public void tryCalendar(){
        authService.defaultLogin();
        webApp.homePage().openNewInvoicePage();
        webApp.newInvoicePage().chooseDateOfIssueAndDateOfSupply("12","12");

    }
}
