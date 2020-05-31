package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import mmt.automation.common.DriverSetup;

public class SearchRoom extends CommonUsage {
    private AndroidDriver driver;

    /**
     * Preset driver to the current instance.
     *
     * @param driver
     */
    public SearchRoom(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    /**
     * Select hotel for the adult and child counts and trip type
     *
     * @param adultcount
     * @param childcount
     * @throws Exception
     */
    public void searchHotel(String location, int adultcount, int childcount) throws Exception {
        AndroidGestures.tap(driver, "Hotel");
        AndroidGestures.tap(driver, "city");
        AndroidGestures.horizontalScroll(driver, location);
        AndroidGestures.tap(driver, "cityName");
        DriverSetup.logMessage("Selected city for booking hotel");
        AndroidGestures.tap(driver, "adultCountHeader");
        AddGuest(driver,"adultCount", "adultAdd", adultcount);
        AddGuest(driver,"childCount", "childAdd", childcount);
        AndroidGestures.tap(driver, "addAnotherRoom");
        AddGuest(driver,"adultCount", "adultAdd", adultcount);
        AddGuest(driver,"childCount", "childAdd", childcount);
        AndroidGestures.tap(driver, "done");
        AndroidGestures.tap(driver, "tripType");
        AndroidGestures.tap(driver, "search");
        DriverSetup.logMessage("search is successful");
    }
}
