package base;

import enums.Language;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import pages.base.WebApp;
import services.AuthService;

public class BaseTest {

    protected WebDriver driver;
    protected WebApp webApp;
    protected AuthService authService;


    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.initDriver();
        driver.manage().window().maximize();
        webApp = new WebApp(driver);
        authService = new AuthService(webApp);



    }







/*
 @AfterMethod
    public void tearDown(){
        driver.quit();
    }
*/



}
