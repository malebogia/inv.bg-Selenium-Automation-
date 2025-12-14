package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.base.BasePage;
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

    public void loginInEn(String email , String password){
        driver.get("https://tester-123.inv.bg/login");
        webApp.enterPage().login("malebogia91@gmail.com","12345678", EnterPage.Language.EN);

    }

    public void loginInBg(String email , String password){
        webApp.enterPage().login("malebogia91@gmail.com","12345678", EnterPage.Language.BG);

    }

  //*  @AfterMethod
  //  public void tearDown(){
  //      driver.quit();
  //  }



}
