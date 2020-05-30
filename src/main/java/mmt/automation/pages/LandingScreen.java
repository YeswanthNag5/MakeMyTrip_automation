package mmt.automation.pages;

import mmt.automation.common.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import org.openqa.selenium.By;

public class LandingScreen {
    private AndroidDriver driver;

    public LandingScreen(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void SelectHotel()throws Exception {
        AndroidGestures.tap(driver, "Hotel");
        AndroidGestures.tap(driver,"city");
        AndroidGestures.horizontalScroll(driver);
        AndroidGestures.tap(driver,"selectCity");
        AndroidGestures.tap(driver,"adultCountHeader");
        while(!(AndroidGestures.getText(driver,"adultCount").equals("2")))
        {
           AndroidGestures.tap(driver,"adultAdd");
        }
        while(!(AndroidGestures.getText(driver,"childCount").equals("2")))
        {
            AndroidGestures.tap(driver,"childAdd");
        }
        AndroidGestures.tap(driver,"done");
        AndroidGestures.tap(driver,"tripType");
        AndroidGestures.tap(driver,"search");
    }
}
