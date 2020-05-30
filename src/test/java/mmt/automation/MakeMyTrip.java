package mmt.automation;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.DriverSetup;
import mmt.automation.pages.Authentication;
import mmt.automation.pages.OrderRoom;
import mmt.automation.pages.SearchRoom;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MakeMyTrip extends DriverSetup {
    AndroidDriver<MobileElement> testdriver;
    Authentication authentication;
    SearchRoom searchRoom;
    OrderRoom orderRoom;


    /**
     * Set current instance Driver to the test.
     *
     * @param uname
     * @param password
     * @throws Exception
     */
    @BeforeClass
    @Parameters({"uname", "password"})
    public void setTestdriver(String uname, String password) throws Exception {
        this.testdriver = getcurrentAndroidThreadDriver();
        authentication = new Authentication(testdriver);
        authentication.signIn(uname, password);
    }

    @Test(priority = 0)
    @Parameters({"adultCount", "childCount"})
    public void test(int adultCount, int childCount) throws Exception {
        this.testdriver = getcurrentAndroidThreadDriver();
        searchRoom = new SearchRoom(testdriver);
        searchRoom.searchHotel(adultCount, childCount);
        logMessage("Searched hotel for"+adultCount+"adults and"+childCount+"children");
    }


    @Test(priority = 1)
    public void test1() throws Exception {
        this.testdriver = getcurrentAndroidThreadDriver();
        orderRoom = new OrderRoom(testdriver);
        orderRoom.sortAndFilter();
        orderRoom.chooseHotel();

    }

    /**
     * SignOut from the App before APP cleanup.
     *
     * @throws Exception
     */
    @AfterClass
    public void tearDown() throws Exception {
        logMessage("Sign out from App");
        authentication.signOut();
        logMessage("Signed out from App");
    }
}

