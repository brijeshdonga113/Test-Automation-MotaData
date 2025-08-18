package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://172.16.11.181/login?redirectFrom=%2Ft%2Frequest%2F");
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        loginPage.enterUsername("brijesh.donga@motadata.com");
        loginPage.enterPassword("admin@123");
        loginPage.clickLogin();

        // Example check (adapt for your app)
        Assert.assertEquals(driver.getTitle(), "Dashboard", "User should be redirected to Dashboard");
    }

    @Test(description = "Verify error message with invalid credentials")
    public void testInvalidLogin() {
        loginPage.enterUsername("wronguser");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid"), "Error message should be displayed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
