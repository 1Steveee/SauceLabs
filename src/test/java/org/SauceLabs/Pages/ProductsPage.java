package org.SauceLabs.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

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


    public void logout() {
        menu().click();
        logoutButton().click();
    }
}
