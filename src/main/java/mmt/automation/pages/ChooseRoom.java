package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;

public class ChooseRoom extends CommonUsage {

    private AndroidDriver driver;

    /**
     * Preset driver to the current instance.
     *
     * @param driver
     */
    public ChooseRoom(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void orderRoom() throws Exception {
        AndroidGestures.tap(driver, "sortAndFilter");
    }

}
