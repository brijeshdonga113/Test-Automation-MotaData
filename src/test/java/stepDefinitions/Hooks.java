package stepDefinitions;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import org.openqa.selenium.WebDriver;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class Hooks extends BaseTest {

    private WebDriver driver;
    private static ExtentTest test;
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ExtentTest stepNode;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println(">>> Starting browser before scenario...");

        // Create ExtentTest node for each scenario
        test = extent.createTest(scenario.getName());

        initDriver();
        driver = getDriver();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        // Log step starting text

    }

    @AfterStep
    public void afterStep(io.cucumber.java.Scenario scenario) {
        // Fetch last executed step using reflection on Cucumber internals
        String stepName = getStepName(scenario);

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "❌ Step failed: " + stepName);

            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, scenario.getName());
            if (screenshotPath != null) {
                try {
                    test.fail("📸 Screenshot on failure",
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } catch (Exception e) {
                    System.out.println("⚠️ Could not attach screenshot: " + e.getMessage());
                }
            }

            String base64Screenshot = ScreenshotUtil.captureScreenshotBase64(driver);
            if (base64Screenshot != null) {
                test.fail("📎 Embedded Screenshot (Base64)")
                        .addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
            }
        } else {
            test.log(Status.PASS, "✅ Step passed: " + stepName);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println(">>> Closing browser after scenario...");

        if (!scenario.isFailed()) {
            test.pass("✅ Scenario passed: " + scenario.getName());
        } else {
            test.fail("❌ Scenario failed: " + scenario.getName());
        }

        quitDriver();
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test;
    }

    // Utility to extract executed step text
    private String getStepName(Scenario scenario) {
        try {
            Object stepObj = scenario.getClass()
                    .getMethod("getTestCase")
                    .invoke(scenario);

            Object testSteps = stepObj.getClass()
                    .getMethod("getTestSteps")
                    .invoke(stepObj);

            for (Object testStep : (Iterable<?>) testSteps) {
                if (testStep instanceof PickleStepTestStep) {
                    return ((PickleStepTestStep) testStep).getStep().getText();
                }
            }
        } catch (Exception e) {
            return "[Unknown Step]";
        }
        return "[Unknown Step]";
    }
}
