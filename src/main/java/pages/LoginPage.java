package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;
import utils.TestContext;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    // MASSA DE DADOS
    String usuario = TestContext.massa.get("usuario");
    String senha = TestContext.massa.get("senha");
    // ELEMENTOS PAGE
    private final By emailInput = By.xpath("//*[@name='email']");
    private final By senhaInput = By.xpath("//*[@name='password']");
    private final By entrarButton = By.xpath("//*[text()='Entrar']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acessarPagina() {
        String url = PropertiesReader.get("url");
        driver.get(url);
    }

    public void preencherUsuario() {
        driver.findElement(emailInput).sendKeys(usuario);
    }

    public void preencherSenha() {
        driver.findElement(senhaInput).sendKeys(senha);
    }

    public LoginPage clicarEntrar() throws InterruptedException {
        driver.findElement(entrarButton).click();
        return new LoginPage(driver);
    }

    public void alertaLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

}
