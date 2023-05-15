package org.SauceLabs;

import io.appium.java_client.android.AndroidDriver;
import org.SauceLabs.Pages.CartPage;
import org.SauceLabs.Pages.CheckOutPage;
import org.SauceLabs.Pages.HomePage;
import org.SauceLabs.Pages.ProductsPage;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SauceLabsTest extends BaseTest {

    private final String LOGIN_ERROR_MESSAGE = "Username and password do not match any user in this service.";
    private AndroidDriver driver;
    private final String STANDARD_USER = "standard_user";
    private final String LOCKED_OUT_USER = "locked_out_user";
    private final String PROBLEM_USER = "problem_user";
    private final String PASSWORD = "secret_sauce";
    private HomePage homePage;
    private ProductsPage productsPage;

    @BeforeClass
    public void setUpTest() {
        this.driver = driverManager.getDriver();
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

        if (username == LOCKED_OUT_USER && correctPassword) {
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
    public void testAddToCartButton() {
        this.productsPage.addSauceLabProduct(1);
        assertEquals("REMOVE", this.productsPage.getRemoveButtonText());

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
    public void testVerifyCartDetails() {
        CartPage cartPage = this.productsPage.addProductAndMoveToCart(2);
        assertEquals("Sauce Labs Backpack", cartPage.getFirstProductName());
        assertEquals("$29.99", cartPage.getFirstProductPrice());
        assertEquals("Sauce Labs Bike Light", cartPage.getSecondProductName());
        assertEquals("$9.99", cartPage.getSecondProductPrice());
    }

    @Test
    public void testVerifyCartDetailsSingleItem() {
        CartPage cartPage = this.productsPage.addProductAndMoveToCart(1);
        assertEquals("Sauce Labs Backpack", cartPage.getFirstProductName());
        assertEquals("$29.99", cartPage.getFirstProductPrice());

        cartPage.addAdditionalProduct();
        assertEquals("Sauce Labs Bike Light", cartPage.getSecondProductName());
        assertEquals("$9.99", cartPage.getSecondProductPrice());
    }

    @Test
    public void testEndToEndTransaction() {
        CartPage cartPage = this.productsPage.addProductAndMoveToCart(1);
        assertEquals("Sauce Labs Backpack", cartPage.getFirstProductName());
        assertEquals("$29.99", cartPage.getFirstProductPrice());

        CheckOutPage checkOutPage = cartPage.proceedToCheckOutPage();
        checkOutPage.checkOut("Steven", "Test", "33803");
        assertEquals("Sauce Labs Backpack", checkOutPage.getProductName());
        assertEquals("$29.99", checkOutPage.getProductPrice());
        assertEquals("SauceCard #31337", checkOutPage.getPaymentDetails());
        assertEquals("FREE PONY EXPRESS DELIVERY!", checkOutPage.getShippingDetails());
        assertEquals("$32.39", checkOutPage.getTotalPrice());

        checkOutPage.completeOrder();
        assertEquals("THANK YOU FOR YOU ORDER", checkOutPage.getThankYouMessage());

    }



    @AfterMethod
    public void logout() {
        this.homePage.logout();
    }
}
