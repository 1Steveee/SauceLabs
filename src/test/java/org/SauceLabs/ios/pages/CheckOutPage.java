package org.SauceLabs.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;

public class CheckOutPage {

    private IOSDriver driver;

    public CheckOutPage(IOSDriver driver) {
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

    private WebElement finishButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-FINISH"));
    }

    private WebElement zipCodeField() {
        return driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code"));
    }

    private WebElement sauceLabsBackPackElement() {
        return driver.findElement(AppiumBy.accessibilityId("Sauce Labs Backpack"));
    }

    public String getSauceLabsBackPackText() {
        return sauceLabsBackPackElement().getText();
    }

    private WebElement completeOrderElement() {
        return driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT: COMPLETE!"));
    }

    private WebElement sauceLabsBackPackPrice() {
        return driver.findElement(AppiumBy.accessibilityId("$29.99"));
    }


    public String getSauceLabsBackPackPrice() {
        return sauceLabsBackPackPrice().getText();
    }

    public String getThankYouMessage() {
        return completeOrderElement().findElement(AppiumBy.accessibilityId("THANK YOU FOR YOU ORDER")).getText();
    }

    public String getPaymentDetails() {
        return driver.findElement(AppiumBy.accessibilityId("SauceCard #31337")).getText();
    }

    public String getShippingDetails() {
        return driver.findElement(AppiumBy.accessibilityId("FREE PONY EXPRESS DELIVERY!")).getText();
    }

    public String getTotalPrice() {
        return driver.findElement(AppiumBy.accessibilityId("Total: $32.39")).getText().split(":")[1].strip();
    }

    public void swipeAndFindElement() {
        WebElement targetCell = driver.findElement(AppiumBy
                .accessibilityId("test-FINISH"));
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", ((RemoteWebElement)targetCell).getId());
        driver.executeScript("mobile: scrollToElement",scrollObject);
    }


    public void checkOut(String firstName, String lastName, String zipCode) {
        firstNameField().sendKeys(firstName);
        lastNameField().sendKeys(lastName);
        zipCodeField().sendKeys(zipCode);
        continueButton().click();
    }

    public void completeOrder() {
        swipeAndFindElement();
        finishButton().click();
    }
}
