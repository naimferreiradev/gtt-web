package steps;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import utils.*;

import java.util.EventListener;
import java.util.Map;

public class Hooks implements EventListener {
    @Before
    public void setup(Scenario scenario) {

        // Inicializa o driver
        DriverFactory.getDriver();

        // Lê a TAG @CT
        String ct = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith("@CT"))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("TAG @CT não encontrada no cenário"))
                .replace("@", "");

        // Carrega a massa do Excel
        TestContext.massa = ExcelReader.getDataByCT(ct);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
