package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;

public class LandingScreen extends CommonUsage {
    private AndroidDriver driver;

    public LandingScreen(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void SelectHotel(String location, int adultcount, int childcount) throws Exception {
        AndroidGestures.tap(driver, "Hotel");
        AndroidGestures.tap(driver, "city");
        AndroidGestures.horizontalScroll(driver, location);
        AndroidGestures.tap(driver, "selectCity");
        AndroidGestures.tap(driver, "adultCountHeader");
        AddGuest(driver,"adultCount", "adultAdd", adultcount);
        AddGuest(driver,"childCount", "childAdd", childcount);
        AndroidGestures.tap(driver, "done");
        AndroidGestures.tap(driver, "tripType");
        AndroidGestures.tap(driver, "search");
    }
}
