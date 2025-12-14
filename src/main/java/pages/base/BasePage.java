package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    //==================
    // Base Logic
    //==================

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public void click(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    public void typeText(WebElement element, String string) {

        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(string);
    }


    // =========================
    // CHECKS (NO ASSERTS)
    // ========================

    public boolean isElementSelected(WebElement webelement) {
        return webelement.isSelected();
    }

    public boolean isElementDisplayed(WebElement webelement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webelement));
            return webelement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //==================
    //  WAITERS
    // ===============



    protected WebElement waitForElementByLocator(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
