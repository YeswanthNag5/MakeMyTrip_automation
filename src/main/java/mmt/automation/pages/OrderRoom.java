package mmt.automation.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import mmt.automation.common.Constants;
import mmt.automation.common.Helper;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class OrderRoom {

    private AndroidDriver driver;

    /**
     * Preset driver to the current instance.
     *
     * @param driver
     */
    public OrderRoom(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void sortAndFilter() throws Exception {
        AndroidGestures.tap(driver, "sortAndFilter");
        Helper.waitTillVisible(driver, "seekBar");
        AndroidGestures.seekBarSwipe(driver, "seekBar", 1000);
        AndroidGestures.scrollIntoView(driver, "userRating", "", 10, Constants.swipeDirection.UP);
        AndroidGestures.tap(driver, "userRating");
        AndroidGestures.tap(driver, "applyFilter");
    }

    public void bookHotel() throws Exception {
        // searchRoom("searchResultHotelName");
        Helper.waitTillVisible(driver, "bookNow");
        if (AndroidGestures.getText(driver, "bookNow").equalsIgnoreCase("SELECT ROOM")) {
            AndroidGestures.tap(driver, "bookNow");
//            System.out.println("Room type" + AndroidGestures.getText(driver, "roomName"));
//            System.out.println("Room Extra info" + AndroidGestures.getText(driver, "roomExtraInfo"));
//            System.out.println("Room More info" + AndroidGestures.getText(driver, "roomMoreInfo"));
//            System.out.println("Room cancel info" + AndroidGestures.getText(driver, "cancelInfo"));
            AndroidGestures.tap(driver, "bookNow");
        } else {
            AndroidGestures.scrollIntoView(driver, "roompayInfo", "", 10, Constants.swipeDirection.UP);
//            System.out.println("Room type" + AndroidGestures.getText(driver, "roomName"));
//            System.out.println("Room Extra info" + AndroidGestures.getText(driver, "roomExtraInfo"));
//            System.out.println("Room More info" + AndroidGestures.getText(driver, "roomMoreInfo"));
//            System.out.println("Room Cancelation info" + AndroidGestures.getText(driver, "cancellationInfo"));
//            System.out.println("Room rent info" + AndroidGestures.getText(driver, "roompayInfo"));
            AndroidGestures.tap(driver, "bookNow");

        }

    }

    public void searchRoom(String locator) throws Exception {
        List<MobileElement> elements = null;
        try {
            elements = Helper.fetchElements(driver, locator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> uniqueNumber = new ArrayList<>();
        if (elements.size() < 5) {
            for (int i = 0; i <= 5; i++) {
                List<MobileElement> elements1 = Helper.fetchElements(driver, locator);
                for (MobileElement ele : elements1) {
                    if (!uniqueNumber.contains(ele.getText())) {
                        uniqueNumber.add(ele.getText());
                    }
                    if (uniqueNumber.size() < 5) {
                        AndroidGestures.windowSwipe(driver, Constants.swipeDirection.UP);
                    }
                    if (uniqueNumber.size() == 4) {
                        ele.click();
                        break;
                    }
                }
            }
        }
    }


}


