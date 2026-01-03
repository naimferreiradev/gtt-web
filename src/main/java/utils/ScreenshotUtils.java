package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtils {

    public static String capturar(String nome) {
        try {
            File src = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            String path = "reports/img/" + System.currentTimeMillis() + ".png";
            new File("reports/img").mkdirs();

            Files.copy(src.toPath(), new File(path).toPath());
            return path;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static String capturar(String nomeCenario) {
//
//        File src = ((TakesScreenshot) DriverFactory.getDriver())
//                .getScreenshotAs(OutputType.FILE);
//
//        String path = "reports/screenshots/" +
//                System.currentTimeMillis() + "_" +
//                nomeCenario.replace(" ", "_") + ".png";
//
//        try {
//            FileUtils.copyFile(src, new File(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return path;
//    }

}
