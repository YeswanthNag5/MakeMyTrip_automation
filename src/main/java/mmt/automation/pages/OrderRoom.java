package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import mmt.automation.common.Constants;
import mmt.automation.common.Helper;

import java.util.ArrayList;
import java.util.List;

public class OrderRoom extends CommonUsage{

    private AndroidDriver driver;
    public  String SelectedHotelName="";

    /**
     * Preset driver to the current instance.
     *
     * @param driver
     */
    public OrderRoom(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    /**
     * Select sort and filter
     *
     * @throws Exception
     */
    public void sortAndFilter() throws Exception {
        AndroidGestures.tap(driver, "sortAndFilter");
        Helper.waitTillVisible(driver, "seekBar");
        AndroidGestures.seekBarSwipe(driver, "seekBar", 1000);
        AndroidGestures.scrollIntoView(driver, "userRating", "", 10, Constants.swipeDirection.UP);
        AndroidGestures.tap(driver, "userRating");
        AndroidGestures.tap(driver, "applyFilter");
    }

    /**
     * book hotel
     *
     * @throws Exception
     * @param hotelNumber
     */
    public void bookHotel(int hotelNumber) throws Exception {
        searchRoom(hotelNumber,"searchResultHotelName");
        SelectedHotelName= AndroidGestures.getText(driver,"hotelName");
        Helper.waitTillVisible(driver, "bookNow");
        if (AndroidGestures.getText(driver, "bookNow").equalsIgnoreCase("SELECT ROOM")) {
            AndroidGestures.tap(driver, "bookNow");
            System.out.println("Room type" + AndroidGestures.getText(driver, "roomName"));
            System.out.println("Room Extra info" + AndroidGestures.getText(driver, "roomExtraInfo"));
            System.out.println("Room More info" + AndroidGestures.getText(driver, "roomMoreInfo"));
            System.out.println("Room cancel info" + AndroidGestures.getText(driver, "cancelInfo"));
            AndroidGestures.tap(driver, "bookNow");
        } else {
            AndroidGestures.scrollIntoView(driver, "roompayInfo", "", 10, Constants.swipeDirection.UP);
            System.out.println("Room type" + AndroidGestures.getText(driver, "roomName"));
            System.out.println("Room Extra info" + AndroidGestures.getText(driver, "roomExtraInfo"));
            System.out.println("Room More info" + AndroidGestures.getText(driver, "roomMoreInfo"));
            System.out.println("Room Cancelation info" + AndroidGestures.getText(driver, "cancellationInfo"));
            System.out.println("Room rent info" + AndroidGestures.getText(driver, "roompayInfo"));
            AndroidGestures.tap(driver, "bookNow");

        }

    }

    /**
     * Select hotel for the adult and child counts and trip type
     *
     * @param locator
     * @throws Exception
     */
    public void searchRoom(int hotelnumber,String locator) throws Exception {
        List<MobileElement> elements = null;
        try {
            elements = Helper.fetchElements(driver, locator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> uniqueNumber = new ArrayList<>();
        if (elements.size() < hotelnumber) {
            for (int i = 0; i <= 10; i++) {
                List<MobileElement> elements1 = Helper.fetchElements(driver, locator);

                for (MobileElement ele : elements1) {
                    if (!uniqueNumber.contains(ele.getText())) {
                        uniqueNumber.add(ele.getText());
                    }
                    if (uniqueNumber.size() == hotelnumber) {
                        ele.click();
                        break;
                    }
                    if (uniqueNumber.size() < hotelnumber) {
                        AndroidGestures.windowSwipe(driver, Constants.swipeDirection.UP);
                    }
                }
                if (uniqueNumber.size() == hotelnumber) {
                    break;
                }
            }
        }
    }


}


