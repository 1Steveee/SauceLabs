package org.SauceLabs.ios;

import io.appium.java_client.ios.IOSDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;

public class AppiumTest extends BaseTest{

    private IOSDriver driver;

    @BeforeClass
    public void setUpTest() {
        driver = iosDriverManager.getDriver();
    }


}
