package org.SauceLabs.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private AndroidDriver driver;
    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }


    private WebElement productName(int index) {
        return driver.findElement(AppiumBy.xpath
                ("(//android.view.ViewGroup[@content-desc=\"test-Description\"])[" + index + "]/android.widget.TextView[1]"));
    }

    private WebElement continueShoppingButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-CONTINUE SHOPPING"));
    }

    private WebElement productPrice(int index) {
        return driver.findElement(AppiumBy.xpath
                ("(//android.view.ViewGroup[@content-desc=\"test-Price\"])[" + index + "]/android.widget.TextView"));
    }

    private WebElement checkOutButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT"));
    }

    public String getFirstProductPrice() {
        return productPrice(1).getText();
    }

    public String getFirstProductName() {
        return productName(1).getText();
    }

    public String getSecondProductPrice() {
        return productPrice(2).getText();
    }

    public String getSecondProductName() {
        return productName(2).getText();
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
