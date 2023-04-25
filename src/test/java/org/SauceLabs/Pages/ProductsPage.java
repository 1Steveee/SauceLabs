package org.SauceLabs.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.SauceLabs.Utillities.Helper.pauseExecution;

public class ProductsPage {

    private AndroidDriver driver;

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private WebElement menu() {
        return driver.findElement(AppiumBy.accessibilityId("test-Menu"));
    }

    private WebElement logoutButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-LOGOUT"));
    }


    private WebElement backPack() {
        return driver.findElements
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\")")).get(0);
    }

    private WebElement cart() {
        return driver.findElement(AppiumBy.accessibilityId("test-Cart"));
    }

    public CartPage addBackpackToCart() {
        backPack().click();
        cart().click();
        pauseExecution(2, driver);
        return new CartPage(driver);
    }

    public String getBackpackPrice() {
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(4).getText();
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
        pauseExecution(2,driver);
    }

}
