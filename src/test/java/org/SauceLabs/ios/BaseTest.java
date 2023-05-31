package org.SauceLabs.ios;

import org.SauceLabs.IOSDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class BaseTest {

    protected IOSDriverManager iosDriverManager;

    @BeforeClass(alwaysRun = true)
    public void testSetUp() throws MalformedURLException {
        iosDriverManager = new IOSDriverManager();
        iosDriverManager.createIOSDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        iosDriverManager.stopDriver();
    }
}
