package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks extends BaseTest {

    private WebDriver driver;

    //@Before
    public void setUp() {
        System.out.println(">>> Starting browser before scenario...");
        initDriver();
        driver = getDriver();
    }

    //@After
    public void tearDown() {
        System.out.println(">>> Closing browser after scenario...");
        quitDriver();
    }
}
