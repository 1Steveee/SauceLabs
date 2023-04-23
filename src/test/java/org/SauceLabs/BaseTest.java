package org.SauceLabs;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class BaseTest {

    protected DriverManager driverManager;

    @BeforeClass(alwaysRun = true)
    public void testSetup() throws MalformedURLException {
        driverManager = new DriverManager();
        driverManager.createAndroidDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driverManager.stopDriver();
    }
}
