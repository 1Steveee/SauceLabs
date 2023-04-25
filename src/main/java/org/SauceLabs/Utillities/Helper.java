package org.SauceLabs.Utillities;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Helper {

    private AndroidDriver driver;

    public static void pauseExecution(int seconds, AndroidDriver driver) {
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofSeconds(seconds)).perform();
    }

}
