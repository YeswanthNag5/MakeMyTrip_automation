package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;

public class CommonUsage {

    private AndroidDriver driver;

    /**
     * Preset driver to the current instance.
     *
     * @param driver
     */
    public CommonUsage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public CommonUsage() {
    }

    /**
     * Adding guests for the hotel search
     *
     * @param GuestCount
     * @param GuestAdd
     * @throws Exception
     */

    public void AddGuest(AndroidDriver<MobileElement> driver,String GuestCount, String GuestAdd,
                         int count) throws Exception {
        Thread.sleep(1000);
        while (!(Integer.parseInt(AndroidGestures.getText(driver, GuestCount)) == count)) {
            AndroidGestures.tap(driver, GuestAdd);
        }
    }
}


