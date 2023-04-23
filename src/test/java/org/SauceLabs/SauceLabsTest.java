package org.SauceLabs;

import io.appium.java_client.android.AndroidDriver;
import org.SauceLabs.Pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.testng.AssertJUnit.assertEquals;

public class SauceLabsTest extends BaseTest {

    private final String LOGIN_ERROR_MESSAGE = "Username and password do not match any user in this service.";
    private AndroidDriver driver;
    private final String STANDARD_USER = "standard_user";
    private final String LOCKED_OUT_USER = "locked_out_user";
    private final String PROBLEM_USER = "problem_user";
    private final String PASSWORD = "secret_sauce";

    @BeforeClass
    public void setUpTest() {
        this.driver = driverManager.getDriver();
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
        HomePage homePage = new HomePage(driver);
        homePage.loginToApp(username,password, isValidLogin);

        if (username == LOCKED_OUT_USER && correctPassword) {
            assertEquals("Sorry, this user has been locked out." ,homePage.getLockedOutUserErrorMessage());
            return;
        }

        if (!isValidLogin) {
            assertEquals(LOGIN_ERROR_MESSAGE ,homePage.getLoginErrorMessage());
        }
    }
}
