package mmt.automation.pages;

import mmt.automation.common.AndroidGestures;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import mmt.automation.common.DriverSetup;
import mmt.automation.common.Helper;
import org.openqa.selenium.By;

public class LandingScreen {
    private AndroidDriver driver;

    public LandingScreen(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void SelectHotel()throws Exception {
        AndroidGestures.tap(driver, "Hotel");
    }
}
