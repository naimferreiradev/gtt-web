package steps;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.*;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.EventListener;

public class Hooks implements EventListener {


    @Before
    public void setup(Scenario scenario) throws Exception {
//        DriverFactory.getDriver();
        PdfReportUtils.iniciar(scenario.getName());


        String ct = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith("@CT"))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("TAG @CT não encontrada no cenário"))
                .replace("@", "");

        TestContext.massa = ExcelReader.getDataByCT(ct);
    }

//    @AfterStep
//    public void printCadaStep(Scenario scenario) {
//
//        ScreenshotUtils.capturar(scenario.getName());
//
//        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
//                .getScreenshotAs(OutputType.BYTES);
//
//        scenario.attach(screenshot, "image/png", "Screenshot");
//    }


    @AfterStep
    public void afterStep(Scenario scenario) throws MalformedURLException {

        String nomeStep = StepLogger.get();
        if (nomeStep == null) {
            nomeStep = "Step executado";
        }

        String caminhoImagem = ScreenshotUtils.capturar(nomeStep);

        PdfReportUtils.adicionarStep(nomeStep, caminhoImagem);
    }

    @After
    public void afterScenario(Scenario scenario) throws FileNotFoundException {

        PdfReportUtils.finalizar(!scenario.isFailed());

        DriverFactory.quitDriver();
    }

//    @After
//    public void fechar () {
//        DriverFactory.quitDriver();
//    }

}