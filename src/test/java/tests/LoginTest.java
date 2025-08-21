package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import base.BaseTest;

public class LoginTest extends BaseTest {

    private WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        initDriver();
        driver = getDriver();
    }

}
