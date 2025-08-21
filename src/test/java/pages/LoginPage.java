package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By usernameField = By.xpath("//input[@id='username']");
    private By passwordField = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//button[@id='login-btn']");

    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(usernameField));
        field.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(passwordField));

        field.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        try {
            // Sleep for 10 seconds
            Thread.sleep(10000); // 10000 milliseconds = 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // handle the exception
        }
    }

}
