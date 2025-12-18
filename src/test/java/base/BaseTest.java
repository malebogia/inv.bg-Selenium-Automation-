package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import pages.base.WebApp;
import pages.frontend.EnterPage;

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
        webApp.enterPage().login(email,password, EnterPage.Language.EN);

    }

    public void loginInBg(String email , String password){
        webApp.enterPage().login(email, password, EnterPage.Language.BG);

    }

  /* @AfterMethod
    public void tearDown(){
        driver.quit();
    }
*/


}
