package org.SauceLabs.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;


import static org.SauceLabs.Utillities.Helper.pauseExecution;

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

    public String getLockedOutUserErrorMessage() {
        pauseExecution(5, driver);
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(0).getText();
    }

    public String getLoginErrorMessage() {
        pauseExecution(5, driver);
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(2).getText();
    }

    public ProductsPage loginToApp(String username, String password) {
        fillLoginFields(username,password);
        loginButton().click();
        return new ProductsPage(driver);
    }

}
