package org.SauceLabs.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebElement;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class ProductsPage {

    private final IOSDriver driver;

    public ProductsPage(IOSDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    private WebElement filterButton() {
        return driver.findElement(AppiumBy.accessibilityId("test-Modal Selector Button"));
    }

    private WebElement priceAscButton() {
        return driver.findElement(AppiumBy.accessibilityId("Price (low to high)"));
    }

    private WebElement priceDescButton() {
        return driver.findElement(AppiumBy.accessibilityId("Price (high to low)"));
    }

    private List<WebElement> products() {
        return driver.findElements(AppiumBy.name("test-Item title"));
    }

    private WebElement sauceLabProduct() {
        return driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`label == \"ADD TO CART\"`][1]"));
    }

    private WebElement nameDescOrderButton() {
        return driver.findElement(AppiumBy.accessibilityId("Name (Z to A)"));
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

    public String[] getProductTitles() {
        List<WebElement> products = products();
        int productsListSize = products().size();
        String[] productTitles = new String[productsListSize];
        for (int i = 0; i < productsListSize; i++) {
            productTitles[i] = products.get(i).getText();
        }

        return productTitles;
    }

    public void filterByNameDesc() {
        filterButton().click();
        nameDescOrderButton().click();
    }

    public void filterByPriceAsc() {
        filterButton().click();
        priceAscButton().click();
    }

    public void filterByPriceDesc() {
        filterButton().click();
        priceDescButton().click();
    }
}
