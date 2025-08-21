package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String scenarioName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotDir = "target/screenshots/";
            Files.createDirectories(Paths.get(screenshotDir));

            String fileName = scenarioName.replace(" ", "_") + "_" + timestamp + ".png";
            String filePath = screenshotDir + fileName;
            File destination = new File(filePath);
            Files.copy(source.toPath(), destination.toPath());

            // return relative path for Extent
            return "./screenshots/" + fileName;
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }

    public static String captureScreenshotBase64(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return ts.getScreenshotAs(OutputType.BASE64);
    }
}
