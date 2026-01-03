package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;

public class SafeWebDriver {
    private final WebDriver driver;

    public SafeWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void safeExecute(Runnable action) {
        try {
            action.run();
        } catch (UnhandledAlertException e) {
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (Exception ignored) {}
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
