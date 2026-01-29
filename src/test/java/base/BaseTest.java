package base;

import annotations.TestId;
import enums.Language;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.base.WebApp;
import services.AuthService;

import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;
    protected WebApp webApp;
    protected AuthService authService;
    protected static final Logger logger =
            LoggerFactory.getLogger(BaseTest.class);


    @BeforeMethod
    public void setUp(Method method){
        TestId testIdAnnotation = method.getAnnotation(TestId.class);
        String testId = (testIdAnnotation != null) ? testIdAnnotation.value() : "TC-UNK";
        MDC.put("testId", testId);
        MDC.put("testName", method.getName());
        logger.info(">>> Execution Started for Test: {}", method.getName());
        driver = DriverFactory.initDriver();
        driver.manage().window().maximize();
        webApp = new WebApp(driver);
        authService = new AuthService(webApp);

    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("TEST FAILED: {}", result.getName());
            // Personal note for memorized: Always attach .getThrowable it prints the stacktrace.
            logger.error("Exception Detail: ", result.getThrowable());
        } else {
            logger.info("TEST PASSED: {}", result.getName());
        }

        /*if (driver != null) {
            driver.quit();
        }*/


        MDC.clear();
    }




}
