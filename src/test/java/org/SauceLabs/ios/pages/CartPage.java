package org.SauceLabs.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private IOSDriver driver;
    public CartPage(IOSDriver driver) {
        this.driver = driver;
    }

    private WebElement sauceLabsBikeLight() {
        return driver.findElement(AppiumBy.accessibilityId("Sauce Labs Bike Light"));
    }

    private WebElement sauceLabsBackpack() {
        return driver.findElement(AppiumBy.accessibilityId("Sauce Labs Backpack"));
    }

    private WebElement sauceLabsBackpackPrice() {
        return driver.findElement(AppiumBy.accessibilityId("$29.99"));
    }

    private WebElement sauceLabsBikeLightPrice() {
        return driver.findElement(AppiumBy.accessibilityId("$9.99"));
    }

    private WebElement continueShoppingButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-CONTINUE SHOPPING"));
    }

    private WebElement checkOutButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT"));
    }

    public String getBackpackPrice() {
        return sauceLabsBackpackPrice().getText();
    }

    public String getSauceLabsBackpackText() {
        return sauceLabsBackpack().getText();
    }

    public String getBikeLightPrice() {
        return sauceLabsBikeLightPrice().getText();
    }

    public String getSauceLabsBikeLightText() {
        return sauceLabsBikeLight().getText();
    }


    public void addAdditionalProduct() {
        continueShoppingButton().click();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductAndMoveToCart(1);
    }



    public CheckOutPage proceedToCheckOutPage() {
        checkOutButton().click();
        return new CheckOutPage(driver);
    }
}
