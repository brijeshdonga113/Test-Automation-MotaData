package stepDefinitions;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import java.io.IOException;

public class Hooks extends BaseTest {

    private WebDriver driver;
    private static ExtentTest test;
    private static ExtentReports extent = ExtentManager.getInstance();

    @Before
    public void setUp(Scenario scenario) {
        System.out.println(">>> Starting browser before scenario...");
        // Create ExtentTest for each scenario
        test = extent.createTest(scenario.getName());

        initDriver();
        driver = getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println(">>> Closing browser after scenario...");

        if (scenario.isFailed()) {
            test.fail("❌ Scenario failed: " + scenario.getName());

            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, scenario.getName());
            if (screenshotPath != null) {
                test.fail("Screenshot on failure",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }
        } else {
            test.pass("✅ Scenario passed: " + scenario.getName());
        }

        quitDriver();
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test;
    }
}
