package org.SauceLabs.ios;

import io.appium.java_client.ios.IOSDriver;
import org.SauceLabs.ios.pages.*;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;


public class AppiumTest extends BaseTest {

    private final String LOGIN_ERROR_MESSAGE = "Username and password do not match any user in this service.";
    private final String STANDARD_USER = "standard_user";
    private final String LOCKED_OUT_USER = "locked_out_user";
    private final String PROBLEM_USER = "problem_user";
    private final String PASSWORD = "secret_sauce";
    private HomePage homePage;
    private ProductsPage productsPage;
    private IOSDriver driver;
    private CartPage cartPage;

    @BeforeClass
    public void setUpTest() {
        this.driver  = iosDriverManager.getDriver();

        this.homePage = new HomePage(driver);
        this.productsPage = new ProductsPage(driver);
    }

    @DataProvider
    public Iterator<Object[]> loginData() {
        ArrayList<Object[]> data = new ArrayList<>();
        data.add(new Object[] {STANDARD_USER, "Testing123", false, false});
        data.add(new Object[] {LOCKED_OUT_USER, "Testing123", false, false});
        data.add(new Object[] {PROBLEM_USER, "Testing1234", false, false});
        data.add(new Object[] {STANDARD_USER, PASSWORD, true, true});
        data.add(new Object[] {LOCKED_OUT_USER, PASSWORD, false,true});
        data.add(new Object[] {PROBLEM_USER, PASSWORD, true, true});
        return data.iterator();
    }


    @Test(dataProvider = "loginData")
    public void testMultipleLogins(String username, String password, Boolean isValidLogin, Boolean correctPassword) {
        this.homePage.loginToApp(username,password, isValidLogin);

        if (username.equals(LOCKED_OUT_USER) && correctPassword) {
            assertEquals("Sorry, this user has been locked out." ,homePage.getLockedOutUserErrorMessage());
            return;
        }

        if (!isValidLogin) {
            assertEquals(LOGIN_ERROR_MESSAGE ,homePage.getLoginErrorMessage());
        }
    }

    @BeforeMethod
    public void login() {
        this.productsPage = this.homePage.loginToApp(STANDARD_USER, PASSWORD);
    }

    @Test
    public void testVerifyQuantityAdded() {
        this.productsPage.addSauceLabProduct(1);
        assertEquals("1", this.productsPage.getCartTotalQuantity());
    }

    @Test
    public void testVerifyQuantityTwoAdded() {
        this.productsPage.addSauceLabProduct(3);
        assertEquals("3", this.productsPage.getCartTotalQuantity());
    }

    @Test
    public void testRemoveProduct() {
        this.productsPage.addSauceLabProduct(2);
        assertEquals("2", this.productsPage.getCartTotalQuantity());
        this.productsPage.removeSauceLabProduct(1);
        assertEquals("1", this.productsPage.getCartTotalQuantity());
    }

    @Test
    public void testAddToCartButton() {
        this.productsPage.addSauceLabProduct(1);
        assertEquals("REMOVE", this.productsPage.getRemoveButtonText());
    }

    @Test
    public void testVerifyCartDetails() {
        this.cartPage = this.productsPage.addProductAndMoveToCart(2);
        assertEquals("Sauce Labs Backpack", cartPage.getSauceLabsBackpackText());
        assertEquals("$29.99", cartPage.getBackpackPrice());
        assertEquals("Sauce Labs Bike Light", cartPage.getSauceLabsBikeLightText());
        assertEquals("$9.99", cartPage.getBikeLightPrice());
    }

    @Test
    public void testVerifyCartDetailsSingleItem() {
        this.cartPage = this.productsPage.addProductAndMoveToCart(1);
        assertEquals("Sauce Labs Backpack", cartPage.getSauceLabsBackpackText());
        assertEquals("$29.99", cartPage.getBackpackPrice());

        cartPage.addAdditionalProduct();
        assertEquals("Sauce Labs Bike Light", cartPage.getSauceLabsBikeLightText());
        assertEquals("$9.99", cartPage.getBikeLightPrice());
    }

    @Test
    public void testEndToEndTransaction() {
        this.cartPage = this.productsPage.addProductAndMoveToCart(1);
        assertEquals("Sauce Labs Backpack", cartPage.getSauceLabsBackpackText());
        assertEquals("$29.99", cartPage.getBackpackPrice());

        CheckOutPage checkOutPage = cartPage.proceedToCheckOutPage();
        checkOutPage.checkOut("Steven", "Test", "33803");
        assertEquals(checkOutPage.getSauceLabsBackPackText(), "Sauce Labs Backpack");
        assertEquals(checkOutPage.getSauceLabsBackPackPrice(), "$29.99");
        assertEquals(checkOutPage.getPaymentDetails(), "SauceCard #31337");
        assertEquals(checkOutPage.getShippingDetails(), "FREE PONY EXPRESS DELIVERY!");
        assertEquals(checkOutPage.getTotalPrice(), "$32.39");

        checkOutPage.completeOrder();
        assertEquals(checkOutPage.getThankYouMessage(), "THANK YOU FOR YOU ORDER");

    }

    @Test
    public void testFilterByNameDesc() {
        String[] sortedProductTitles = new String[] {"Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Onesie", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt","Sauce Labs Bike Light", "Sauce Labs Backpack"};
        this.productsPage.filterByNameDesc();
        assertArrayEquals(sortedProductTitles, this.productsPage.getProductTitles());
    }

    @Test
    public void testFilterByPriceAsc() {
        String[] sortedProductTitles = new String[] {"Sauce Labs Onesie","Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Backpack", "Sauce Labs Fleece Jacket"};
        this.productsPage.filterByPriceAsc();
        assertArrayEquals(sortedProductTitles, this.productsPage.getProductTitles());
    }

    @Test
    public void testFilterByPriceDesc() {
        String[] sortedProductTitles = new String[] {"Sauce Labs Fleece Jacket","Sauce Labs Backpack", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light","Sauce Labs Onesie"};
        this.productsPage.filterByPriceDesc();
        assertArrayEquals(sortedProductTitles, this.productsPage.getProductTitles());
    }

    @Test
    public void testRemoveProductFromCart() {
        this.cartPage = this.productsPage.addProductAndMoveToCart(2);
        assertEquals(this.cartPage.getCartQuantity(), 2);

        this.cartPage.removeProductAtPosition(2);
        assertEquals(this.cartPage.getCartQuantity(), 1);
    }

    @Test
    public void testValidateProductDetails() {
        String backPackTitle = this.productsPage.getBackPackTitle();
        String backPackPrice = this.productsPage.getBackPackPrice();

        assertEquals(backPackTitle, "Sauce Labs Backpack");
        assertEquals(backPackPrice, "$29.99");

        ProductDetail productDetail = this.productsPage.goToBackPackProductDetails();

        assertEquals(productDetail.getBackPackTitle(), backPackTitle);
        assertEquals(productDetail.getProductPrice(), backPackPrice);

    }


    @AfterMethod
    public void logout() {
        this.homePage.logout();
    }
}
