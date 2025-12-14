package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static WebDriver initDriver() {
        return new ChromeDriver();
    }
}
