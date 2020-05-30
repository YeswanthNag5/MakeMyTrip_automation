package mmt.automation.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mmt.automation.common.AndroidGestures;
import mmt.automation.common.DriverSetup;
import mmt.automation.common.Helper;

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
        if (AndroidGestures.isElementVisible(driver, "clearUser")) {
            AndroidGestures.tap(driver, "clearUser");
        }
        AndroidGestures.setEditValue(driver, "username", userName);
        AndroidGestures.tap(driver, "continue");
        AndroidGestures.tap(driver, "viapassword");
        AndroidGestures.setEditValue(driver, "password", password);
        Helper.hideKeyboard(driver);
        AndroidGestures.tap(driver, "SignIn");
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


