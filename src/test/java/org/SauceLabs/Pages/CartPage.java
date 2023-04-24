package org.SauceLabs.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CartPage {

    private AndroidDriver driver;
    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public String getProductName() {
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(5).getText();
    }


    public String getProductPrice() {
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(8).getText();
    }
}
