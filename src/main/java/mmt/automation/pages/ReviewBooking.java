package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import mmt.automation.common.Constants;
import mmt.automation.common.Helper;
import org.testng.Assert;

public class ReviewBooking extends CommonUsage {
    private AndroidDriver driver;

    /**
     * Preset driver to the current instance.
     *
     * @param driver
     */
    public ReviewBooking(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void guestDetails(String firstName, String lastName, String email, String phone) throws Exception {
        AndroidGestures.tap(driver, "title");
        AndroidGestures.tap(driver, "Mr");
        AndroidGestures.setEditValue(driver, "firstName", firstName);
        AndroidGestures.setEditValue(driver, "lastName", lastName);
        AndroidGestures.setEditValue(driver, "email", email);
        if (!AndroidGestures.isElementVisible(driver, "phone")) {
            Helper.hideKeyboard(driver);
        }
        AndroidGestures.setEditValue(driver, "phone", phone);
        Helper.hideKeyboard(driver);
    }


    public void specialRequest() throws Exception {
        AndroidGestures.tap(driver, "specialRequest");
        Helper.waitTillVisible(driver, "smokingRoomCheckbox");
        if (!AndroidGestures.isChecked(driver, "smokingRoomCheckbox")) {
            AndroidGestures.tap(driver, "smokingRoomCheckbox");
            Assert.assertTrue(AndroidGestures.isChecked(driver,
                    "smokingRoomCheckbox"), "verify smoking checkbox is checked");
        }
        Helper.waitTillVisible(driver, "largeBedCheckbox");
        if (!AndroidGestures.isChecked(driver, "largeBedCheckbox")) {
            AndroidGestures.tap(driver, "largeBedCheckbox");
            Assert.assertTrue(AndroidGestures.isChecked(driver,
                    "largeBedCheckbox"), "verify Large bed checkbox is checked");
        }
        AndroidGestures.tap(driver, "doneBtn");
    }

    public void uncheckMMTfoundationDonation() throws Exception {
        AndroidGestures.scrollIntoView(driver, "mmtFoundationDonation", "", 10, Constants.swipeDirection.UP);
        if (AndroidGestures.isChecked(driver, "mmtFoundationDonation")) {
            AndroidGestures.tap(driver, "mmtFoundationDonation");
            Assert.assertFalse(AndroidGestures.isChecked(driver,
                    "mmtFoundationDonation"), "verify MMT foundation Donation is unchecked");
        }
        AndroidGestures.tap(driver, "bookNow");

    }

    public void verifySelectedDetails(int adultCount, int childCount) throws Exception {
        AndroidGestures.tap(driver, "detailsChevron");
        Assert.assertEquals("verify total number of guests", AndroidGestures.getText(driver, "totalGuest"), (adultCount + childCount) + " Guests");

    }


}
