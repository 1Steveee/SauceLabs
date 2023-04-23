package org.SauceLabs;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private AndroidDriver driver;
    private final String APP_PATH = System.getProperty ("user.home") + "\\Downloads\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
    private final String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub";

    private UiAutomator2Options UiAutomator2Options() {

        UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options()
                .setDeviceName("Pixel 6 Pro API 30")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity("com.swaglabsmobileapp.MainActivity")
                .setApp(APP_PATH)
                .setNoReset(false)
                .setPlatformName("Android");
        return uiAutomator2Options;
    }

    public void createAndroidDriver() throws MalformedURLException {
        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), UiAutomator2Options());
        setUpDriverTimeOuts();
    }

    private void setUpDriverTimeOuts() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void stopDriver() {
        driver.quit();
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}
