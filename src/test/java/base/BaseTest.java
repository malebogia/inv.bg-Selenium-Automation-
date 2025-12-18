package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import pages.base.WebApp;
import pages.frontend.LoginPage;

public class BaseTest {

    protected WebDriver driver;
    protected WebApp webApp;

    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.initDriver();
        driver.manage().window().maximize();
        webApp = new WebApp(driver);


    }

/* /**
 * These two methods are used for tests on pages that require authentication.
 * They perform login first, after which the page-specific method
 * openPage() should be called.
 */

    public void loginInEn(String email , String password){
        driver.get("https://tester-123.inv.bg/login");
        webApp.loginPage().login(email,password, LoginPage.Language.EN);

    }

    public void loginInBg(String email , String password){
        webApp.loginPage().login(email, password, LoginPage.Language.BG);

    }

  /* @AfterMethod
    public void tearDown(){
        driver.quit();
    }
*/


}
