package org.SauceLabs;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.SauceLabs.devices.IOSDevice;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

import static org.SauceLabs.devices.DeviceDataBuilder.registerIOSDevice;

public class IOSDriverManager {
    private IOSDriver driver;
    private final String APP_PATH = String
        .valueOf(Path.of(System.getProperty
                ("user.dir"),"/src/test/resources/app", "iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.app"));


    private XCUITestOptions xcuiTestOptions() throws FileNotFoundException {
        IOSDevice iosDevice = registerIOSDevice();
        return new XCUITestOptions()
                .setPlatformName(iosDevice.getPlatformName())
                .setPlatformVersion(iosDevice.getPlatformVersion())
                .setDeviceName(iosDevice.getDeviceName())
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setApp(APP_PATH)
                .setNoReset(false);
    }

    public void createIOSDriver() throws MalformedURLException, FileNotFoundException {
        String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub";
        driver = new IOSDriver(new URL(APPIUM_SERVER_URL),xcuiTestOptions());
        setUpDriverTimeOuts();
    }

    private void setUpDriverTimeOuts() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void stopDriver() {
        driver.quit();
    }

    public IOSDriver getDriver() {
        return driver;
    }
}
