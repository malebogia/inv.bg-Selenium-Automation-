    package frontend;

    import base.BaseTest;
    import enums.InvoiceSearchType;
    import org.openqa.selenium.WebElement;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;
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

        @DataProvider
        public Object[][] invoiceSearchData(){
            return new Object[][]{
                    {InvoiceSearchType.INVOICE_NUMBER,"0000000043","Janice Cronin"},
                    {InvoiceSearchType.CLIENT_NAME,"Janice Cronin","Janice Cronin"},
                    {InvoiceSearchType.COMPANY_NUMBER,"379","Janice Cronin"},
                    {InvoiceSearchType.RECIPIENT,"Armando Paucek","Janice Cronin"}
            };
        }


        @Test(dataProvider = "invoiceSearchData")
        public void searchInvoiceTest(InvoiceSearchType type,String value,String expectedResult){

            super.loginInEn("malebogia91@gmail.com", "12345678");
            webApp.invoicesListPage().openPage();

            String resultText = webApp.invoicesListPage().searchInvoice(type,value);

            Assert.assertEquals(resultText,expectedResult);


        }


    }
