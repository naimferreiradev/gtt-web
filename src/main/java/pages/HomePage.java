package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void validarHomepageBoasvindas() {
        WebElement boasvindas = driver.findElement(By.xpath( "//*[contains(text(), 'Bem-vindo de volta, ')]"));
        if (boasvindas.isDisplayed()) {
            System.out.println("Element is visible.");
        } else {
            System.out.println("Element is NOT visible.");
        }
    }

}
