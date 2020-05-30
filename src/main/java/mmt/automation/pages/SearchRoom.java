package mmt.automation.pages;

import mmt.automation.common.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import org.openqa.selenium.By;

public class SearchRoom {
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
    public void searchHotel(int adultcount,int childcount)throws Exception {
        AndroidGestures.tap(driver, "Hotel");
        AndroidGestures.tap(driver,"city");
        AndroidGestures.horizontalScroll(driver);
        AndroidGestures.tap(driver,"cityName");
        DriverSetup.logMessage("Selected city for booking hotel");
        AndroidGestures.tap(driver,"adultCountHeader");
        AddGuest("adultCount","adultAdd",adultcount);
        AddGuest("childCount","childAdd",childcount);
        AndroidGestures.tap(driver,"done");
        AndroidGestures.tap(driver,"tripType");
        AndroidGestures.tap(driver,"search");
        DriverSetup.logMessage("search is successful");
    }

    /**
     * Adding guests for the hotel search
     *
     * @param GuestCount
     * @param GuestAdd
     * @throws Exception
     */

    public void AddGuest(String GuestCount,String GuestAdd,int count) throws Exception
    {
        while(!(Integer.parseInt(AndroidGestures.getText(driver,GuestCount))== count))
        {
            AndroidGestures.tap(driver,GuestAdd);
        }

    }
}
