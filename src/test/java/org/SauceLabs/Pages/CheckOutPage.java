package org.SauceLabs.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CheckOutPage {

    private AndroidDriver driver;

    public CheckOutPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private WebElement firstNameField() {
        return driver.findElement(AppiumBy.accessibilityId("test-First Name"));
    }

    private WebElement lastNameField() {
        return driver.findElement(AppiumBy.accessibilityId("test-Last Name"));
    }

    private WebElement continueButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-CONTINUE"));
    }

    private WebElement zipCodeField() {
        return driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code"));
    }

    private WebElement productDescription() {
        return driver.findElement(AppiumBy.accessibilityId("test-Description"));
    }

    public String getProductName() {
        return productDescription().findElement(AppiumBy.className("android.widget.TextView")).getText();
    }


    private WebElement productPrice() {
        return driver.findElement(AppiumBy.accessibilityId("test-Price"));
    }

    public String getProductPrice() {
        return productPrice().findElement(AppiumBy.className("android.widget.TextView")).getText();
    }

    public String getPaymentDetails() {
        return driver.findElement(AppiumBy.xpath
                ("//android.widget.ScrollView" +
                        "[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/" +
                        "android.view.ViewGroup/android.widget.TextView[2]")).getText();
    }

    public String getShippingDetails() {
        return driver.findElement(AppiumBy.xpath
                ("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/" +
                "android.view.ViewGroup/android.widget.TextView[4]")).getText();
    }

    public String getTotalPrice() {
        return driver.findElement(AppiumBy.xpath("//android.widget.ScrollView" +
                "[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/" +
                "android.view.ViewGroup/android.widget.TextView[7]")).getText().split(":")[1].strip();
    }


    public void checkOut(String firstName, String lastName, String zipCode) {
        firstNameField().sendKeys(firstName);
        lastNameField().sendKeys(lastName);
        zipCodeField().sendKeys(zipCode);
        continueButton().click();
    }
}
