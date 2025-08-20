package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;

import java.time.Duration;

public class LoginSteps extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver.get("http://172.16.11.181/login");
        System.out.println("I am on the login page");

        loginPage = new LoginPage(driver);

    }

    @When("I enter a valid username and password")
    public void iEnterAValidUsernameAndPassword() {
        // Wait until username field is visible
        loginPage.enterUsername("brijesh.donga@motadata.com");

        loginPage.enterPassword("admin@123");




        System.out.println("I enter a valid username and password");
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToTheDashboard() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"));

    }

    @And("I should see a welcome message")
    public void iShouldSeeAWelcomeMessage() {
        System.out.println("I should see a welcome message");
    }
}
