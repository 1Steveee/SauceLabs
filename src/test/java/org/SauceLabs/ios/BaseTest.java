package org.SauceLabs.ios;

import org.SauceLabs.IOSDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class BaseTest {

    protected IOSDriverManager iosDriverManager;

    @Parameters("deviceName")
    @BeforeClass(alwaysRun = true)
    public void testSetUp(final String deviceName) throws MalformedURLException, FileNotFoundException {
        iosDriverManager = new IOSDriverManager(deviceName);
        iosDriverManager.createIOSDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        iosDriverManager.stopDriver();
    }
}
