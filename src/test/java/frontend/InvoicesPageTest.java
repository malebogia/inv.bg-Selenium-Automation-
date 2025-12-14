package frontend;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvoicesPageTest extends BaseTest {

    /**
     * For this class, use the helper method `super.loginEN(String email, String password)`
     * or `super.loginBG(String email, String password)`, depending on the required language.
     * These methods are in `BaseTest` because the page under test requires login.
     */



    @Test
    public void TestInvoicesPage(){
        loginInEn("malebogia91@gmail.com", "12345678");
        webApp.invoicesListPage().openPage();
        webApp.invoicesListPage().selectCheckBoxByValue("40");


    }




}
