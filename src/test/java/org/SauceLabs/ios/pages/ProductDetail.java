package org.SauceLabs.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

public class ProductDetail {
    private IOSDriver driver;

    public ProductDetail(IOSDriver driver) {
        this.driver = driver;
    }

    private WebElement backPackTitle() {
        return driver.findElement(AppiumBy.accessibilityId("Sauce Labs Backpack"));
    }

    private WebElement productPrice() {
        return driver.findElement(AppiumBy.accessibilityId("test-Price"));
    }

    public String getBackPackTitle() {
        return backPackTitle().getText();
    }

    public String getProductPrice() {
        return productPrice().getText();
    }
}
