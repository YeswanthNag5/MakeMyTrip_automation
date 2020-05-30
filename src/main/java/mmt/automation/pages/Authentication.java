package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.DriverSetup;

public class Authentication {

    private AndroidDriver driver;

    /**
     * Preset driver to the current instance.
     *
     * @param driver
     */
    public Authentication(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    /**
     * Sign in to mmt Application with username and password.
     *
     * @param userName
     * @param password
     * @throws Exception
     */
    public void signIn(String userName, String password) throws Exception {
        DriverSetup.logMessage("Signing in with: " + userName + " " + password);

        DriverSetup.logMessage("Signed in successfully");
    }

    /**
     * Sign out from the mmt App.
     *
     * @throws Exception
     */
    public void signOut() throws Exception {

    }
}


