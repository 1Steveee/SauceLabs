package org.SauceLabs.ios.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private IOSDriver driver;
    public CartPage(IOSDriver driver) {
        this.driver = driver;
    }

    public WebElement sauceLabsBikeLight() {
        return driver.findElement(AppiumBy.accessibilityId("Sauce Labs Bike Light"));
    }

    private List<WebElement> productsInCart() {
        return driver.findElements(AppiumBy.accessibilityId("test-Item"));
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

    private List<WebElement> deleteButtons() {
        return driver.findElements(AppiumBy.name("test-Delete"));
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

    public void removeProductAtPosition(int position) {
        int offSetIndex = 1;
        removeProduct(position - offSetIndex);
    }

    private void removeProduct(int position) {
        WebElement product = productsInCart().get(position);
        WebElement deleteButton = deleteButtons().get(position);
        Point productPosition = getElementCenter(product);

        int mainPosition = 0;
        WebElement targetProduct = productsInCart().get(mainPosition);
        Point targetProductPosition = getElementCenter(targetProduct);

        swipeToPosition(productPosition, targetProductPosition);

        Point currentPosition = getElementCenter(product);
        swipeAndRemoveProduct(currentPosition, deleteButton);
    }

    private void swipeAndRemoveProduct(Point currentPosition, WebElement deleteButton) {
        Point targetPosition = new Point((currentPosition.x)/2, currentPosition.y);
        swipeToPosition(currentPosition, targetPosition);
        deleteButton.click();
    }

    private Point getElementCenter(WebElement element) {
        var location = element.getLocation();
        var size = element.getSize();

        return new Point(location.getX() + (size.getWidth()/2), location.getY() + (size.getHeight()/2));
    }


    private void swipeToPosition(Point productPosition, Point targetPosition) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger,1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(), (int) productPosition.x, (int) productPosition.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin
                .viewport(), (int) targetPosition.x  , (int) targetPosition.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger, Duration.ofMillis(1000)));
        driver.perform(List.of(swipe));
    }

    public int getCartQuantity() {
        return productsInCart().size();
    }




}
