package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import mmt.automation.common.Helper;
import org.testng.Assert;

public class PaymentDetails {
    private AndroidDriver driver;
    ReviewBooking reviewBooking;

    /**
     * Preset driver to the current instance.
     *
     * @param driver
     */
    public PaymentDetails(AndroidDriver<MobileElement> driver)
    {
        this.driver = driver;
    }

    /**
     * Sverify with the selected values
     *
     * @param adultCount
     * @param childCount
     * @param hotelLocation
     * @throws Exception
     */

    public void verifySelectedDetails(int adultCount, int childCount,String hotelLocation) throws Exception {
        reviewBooking= new ReviewBooking(driver);
        AndroidGestures.tap(driver, "detailsChevron");
        Helper.waitTillVisible(driver,"HotelNamePayment");
        Assert.assertEquals("Verify the Selected hotel name in  the payment screen",reviewBooking.hotelName,
                AndroidGestures.getText(driver,
                "HotelNamePayment"));
        Assert.assertTrue(AndroidGestures.getText(driver,
                "PlaceNamePayment").contains(hotelLocation),"Verify the Selected city  in  the payment screen");
        Assert.assertEquals("verify total number of guests",reviewBooking.totalGuest,
                AndroidGestures.getText(driver, "totalGuest"));
        Assert.assertEquals("verify total number of Rooms",reviewBooking.totalRoomNo,
                AndroidGestures.getText(driver, "totalRoom"));
        Assert.assertEquals("Verify Checkin date",reviewBooking.checkInDate,AndroidGestures.getText(driver, "checkinDate"));
        Assert.assertEquals("Verify CheckOut date",reviewBooking.checkOutDate,AndroidGestures.getText(driver,
                "checkOutDate"));
    }
    }
