package org.SauceLabs.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

   private WebElement userNameField() {
        return driver.findElement(AppiumBy.accessibilityId("test-Username"));
   }

    private WebElement passwordField() {
        return driver.findElement(AppiumBy.accessibilityId("test-Password"));
    }

    private WebElement loginButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
    }

    private void fillLoginFields(String username, String password) {
        userNameField().clear();
        userNameField().sendKeys(username);
        passwordField().clear();
        passwordField().sendKeys(password);
    }

    private WebElement loginErrorMessage() {
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(2);
    }


    public void loginToApp(String username, String password, Boolean isValidLogin) {
        fillLoginFields(username,password);
        loginButton().click();

        if (isValidLogin) {
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.logout();
        }
    }

    private void pauseForErrorMessage() {
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofSeconds(5)).perform();
    }

    public String getLockedOutUserErrorMessage() {
        pauseForErrorMessage();
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(0).getText();
    }

    public String getLoginErrorMessage() {
        pauseForErrorMessage();
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(2).getText();
    }

}
