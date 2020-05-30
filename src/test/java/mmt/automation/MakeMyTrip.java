package mmt.automation;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.DriverSetup;
import mmt.automation.pages.Authentication;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class MakeMyTrip extends DriverSetup {
    AndroidDriver<MobileElement> testdriver;
    Authentication authentication;


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

