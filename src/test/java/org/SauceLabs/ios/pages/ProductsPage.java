package org.SauceLabs.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebElement;
import java.time.Duration;
import java.util.HashMap;

public class ProductsPage {

    private final IOSDriver driver;

    public ProductsPage(IOSDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    private WebElement sauceLabProduct() {
        return driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`label == \"ADD TO CART\"`][1]"));

    }


    private WebElement cart() {
        return driver.findElement(AppiumBy.accessibilityId("test-Cart"));
    }

    private WebElement removeButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-REMOVE"));
    }

    public void addSauceLabProduct(int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (i < 2) {
                sauceLabProduct().click();
            } else {
                swipeAndFindElement();
                sauceLabProduct().click();
            }

        }
    }



    public void swipeAndFindElement() {
        WebElement targetCell = driver.findElement(AppiumBy
                .iOSClassChain("**/XCUIElementTypeOther[`label == \"ADD TO CART\"`][5]"));
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", ((RemoteWebElement)targetCell).getId());
        driver.executeScript("mobile: scrollToElement",scrollObject);
    }

    public void removeSauceLabProduct(int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (i == 2) {

                removeButton().click();
            }
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
        return cart().getText();
    }

    public CartPage addProductAndMoveToCart(int quantity) {
        addSauceLabProduct(quantity);
        cart().click();
        return new CartPage(driver);
    }

}
