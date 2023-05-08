package org.SauceLabs.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.beanutils.WrapDynaBean;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.SauceLabs.Utillities.Helper.pauseExecution;

public class ProductsPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private WebElement sauceLabProduct() {
        return driver.findElements
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"ADD TO CART\")")).get(0);

    }


    private WebElement cart() {
        return driver.findElement(AppiumBy.accessibilityId("test-Cart"));
    }

    private WebElement removeButton() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"REMOVE\")"));
    }

    private WebElement cartTotalQuantity() {
        return cart().findElement(AppiumBy.className("android.widget.TextView"));
    }

    public void addSauceLabProduct(int quantity) {
        for (int i = 0; i < quantity; i++) {
            sauceLabProduct().click();
        }
    }

    public void removeSauceLabProduct(int quantity) {
        for (int i = 0; i < quantity; i++) {
            removeButton().click();
        }
    }


    public String getBackpackPrice() {
        return driver.findElements(AppiumBy.className("android.widget.TextView")).get(4).getText();
    }


    public String getRemoveButtonText() {
        return removeButton().getText();
    }


    public String getCartTotalQuantity() {
        return cartTotalQuantity().getText();
    }

    public CartPage addProductAndMoveToCart(int quantity) {
        addSauceLabProduct(quantity);
        cart().click();
        return new CartPage(driver);
    }



}
