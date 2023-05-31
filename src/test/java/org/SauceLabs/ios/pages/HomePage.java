package org.SauceLabs.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

import static org.SauceLabs.Utillities.Helper.pauseExecution;

public class HomePage {

    private final IOSDriver driver;

    public HomePage(IOSDriver driver) {
        this.driver = driver;
    }

    private WebElement userNameField() {
        return driver.findElement(AppiumBy.accessibilityId("test-Username"));
    }

    private WebElement menu() {
        return driver.findElement(AppiumBy.accessibilityId("test-Menu"));
    }

    private WebElement logoutButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-LOGOUT"));
    }

    private WebElement passwordField() {
        return driver.findElement(AppiumBy.accessibilityId("test-Password"));
    }

    public WebElement loginButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
    }

    private void fillLoginFields(String username, String password) {
        userNameField().clear();
        userNameField().sendKeys(username);
        passwordField().clear();
        passwordField().sendKeys(password);
    }

    private WebElement loginErrorMessage() {
        return driver.findElement(AppiumBy
                .accessibilityId("Username and password do not match any user in this service."));
    }

    public void logout() {
        menu().click();
        logoutButton().click();

    }

    public boolean verifyLogOutButton() {
        menu().click();
        return logoutButton().isDisplayed();
    }

    public void closeMenu() {
        menu().click();
    }


    public void loginToApp(String username, String password, Boolean isValidLogin) {
        fillLoginFields(username,password);
        loginButton().click();

        if (isValidLogin) {

            logout();
        }
    }

    public String getLockedOutUserErrorMessage() {
        return driver.findElement(AppiumBy.accessibilityId("Sorry, this user has been locked out.")).getText();
    }

    public String getLoginErrorMessage() {
        return loginErrorMessage().getText();
    }

    public ProductsPage loginToApp(String username, String password) {
        fillLoginFields(username,password);
        loginButton().click();
        return new ProductsPage(driver);
    }

    public String getTitle() {
        return driver
                .findElement(AppiumBy
                        .accessibilityId("The currently accepted usernames for this application are (tap to autofill):"))
                .getText();
    }
}
