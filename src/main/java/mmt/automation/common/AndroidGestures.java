package mmt.automation.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import mmt.automation.common.Constants.swipeDirection;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndroidGestures {

    static int defaultTimeout = 30;
    static int sleepinMillis = 100;

    /**
     * Scrolls to the WebElement if it is not on the visible screen.
     *
     * @param driver
     * @param locator
     * @param value
     * @param limit
     * @param thisDirection
     * @throws Exception
     */
    public static void scrollIntoView(AppiumDriver<MobileElement> driver, String locator, String value, int limit,
                                      swipeDirection thisDirection) throws Exception {

        By thisBy = null;
        int i = 0;

        if (value != null && value != "") {
            thisBy = GetUIElements.getProperties(locator, value);
        } else {
            thisBy = GetUIElements.getProperties(locator);
        }

        try {
            while (!(isElementVisible(driver, locator)) && i < limit) {
                windowSwipe(driver, thisDirection);
                i++;
            }
            DriverSetup.logMessage("Scrolled to find Locator : " + locator);
        } catch (Exception e) {
            DriverSetup.logMessage("MobileElement Locator : " + locator + " not found to be scrolled");
            throw e;
        }
    }

    /**
     * Scrolls and Taps on the WebElement if it is not on the visible screen.
     *
     * @param driver
     * @param locator
     * @param value
     * @throws Exception
     */
    public static void scrollFindToTap(AppiumDriver<MobileElement> driver, String locator, String value) throws
            Exception {
        try {
            scrollIntoView(driver, locator, value, 10, swipeDirection.UP);
            if (value != null && value != "") {
                tap(driver, locator);
            } else {
                tap(driver, locator, value);
            }
        } catch (Exception e) {
            DriverSetup.logMessage("Unable to tap on element to Scroll with Locator : " + locator);
            throw e;
        }
    }

    /**
     * Taps on the WebElement by any kind of locator
     *
     * @param driver
     * @param locator
     * @throws Exception
     */
    public static void tap(AppiumDriver<MobileElement> driver, String locator) throws Exception {
        By thisBy = null;

        try {
            thisBy = GetUIElements.getProperties(locator);
            new WebDriverWait(driver, defaultTimeout, sleepinMillis).until(
                    ExpectedConditions.elementToBeClickable(thisBy));
            new TouchAction<>(driver).tap(ElementOption.element(driver.findElement(thisBy))).release().perform();
        } catch (Exception e) {
            throw new Exception("[ERROR] Unable to Tap on " + locator);
        }
    }

    /**
     * GetText of the MobileElement by any kind of locator
     *
     * @param driver
     * @param locator
     * @throws Exception
     */
    public static String getText(AppiumDriver<MobileElement> driver, String locator) throws Exception {
        By thisBy = null;

        try {
            thisBy = GetUIElements.getProperties(locator);
            return driver.findElement(thisBy).getText();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Move the seekBar as per the percentageOfSlide
     *
     * @param driver
     * @param locator
     * @throws Exception
     */
    public static void seekBarSwipe(AppiumDriver<MobileElement> driver, String locator,
                                    int percentageOfSlide) throws Exception {
        driver.context("NATIVE_APP");
        MobileElement seek_bar = driver.findElement(GetUIElements.getProperties(locator));
        int start = seek_bar.getLocation().getX();
        int end = seek_bar.getSize().getWidth();
        int y = seek_bar.getLocation().getY();
        TouchAction action = new TouchAction(driver);
        int moveTo = (int) (end * (0.075));
        action.press(PointOption.point(start, y)).moveTo(PointOption.point(moveTo, y)).release().perform();
    }


    /**
     * check element is visible in the screen
     *
     * @param driver
     * @param locator
     * @throws Exception
     */
    public static boolean isElementVisible(AppiumDriver<MobileElement> driver, String locator) throws Exception {
        By thisBy = null;
        boolean isElementVisible = false;

        try {
            thisBy = GetUIElements.getProperties(locator);
            isElementVisible = driver.findElement(thisBy).isDisplayed();
            return isElementVisible;
        } catch (
                RuntimeException re) {
            return false;
        }

    }

    /**
     * check element is visible in the screen
     *
     * @param driver
     * @param locator
     * @throws Exception
     */
    public static boolean isChecked(AppiumDriver<MobileElement> driver, String locator) throws Exception {
        By thisBy = null;
        String isChecked = "";

        try {
            thisBy = GetUIElements.getProperties(locator);
            isChecked = driver.findElement(thisBy).getAttribute("Checked");
            if (Boolean.parseBoolean(isChecked))
                return true;
        } catch (RuntimeException re) {
            re.printStackTrace();

        }

        return false;
    }


    /**
     * Taps on the WebElement
     *
     * @param driver
     * @param element
     * @throws Exception
     */
    public static void tap(AppiumDriver<MobileElement> driver, MobileElement element) throws Exception {
        try {
            new WebDriverWait(driver, defaultTimeout, sleepinMillis).until(
                    ExpectedConditions.elementToBeClickable(element));
            new TouchAction<>(driver).tap(ElementOption.element(element)).release().perform();
        } catch (Exception e) {
            throw new Exception("[ERROR] Unable to Tap on " + element);
        }
    }


    public static void horizontalScroll(AppiumDriver<MobileElement> driver, String cityName) {
        MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.makemytrip:id/rv_popular_cities\")).setAsHorizontalList().scrollIntoView("
                        + "new UiSelector().textContains(\""+cityName+"\"))"));
    }

    /**
     * Taps on the WebElement based on locator and any attributes value.
     *
     * @param driver
     * @param locator
     * @param value   Defines the element in findElements
     * @throws Exception
     */
    public static void tap(AppiumDriver<MobileElement> driver, String locator, String value) throws Exception {
        By thisBy = null;

        try {
            if (value != null && value != "") {
                thisBy = GetUIElements.getProperties(locator, value);
            } else {
                thisBy = GetUIElements.getProperties(locator);
            }
            new WebDriverWait(driver, defaultTimeout, sleepinMillis).until(ExpectedConditions.elementToBeClickable(thisBy));
            new TouchAction<>(driver).tap(ElementOption.element(driver.findElement(thisBy))).release().perform();
        } catch (Exception e) {
            throw new Exception("[ERROR] Unable to Tap on " + locator + " with value " + value);
        }
    }

    /**
     * Does the Swipe action based on the Swipe types specified in constants.java
     *
     * @param driver
     * @param thisSwipe
     * @throws Exception
     */
    public static void windowSwipe(AppiumDriver<MobileElement> driver, swipeDirection thisSwipe) throws Exception {
        driver.context("NATIVE_APP");
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = size.height / 2;
        int leftX = (int) (size.width * 0.10);
        int righX = (int) (size.width * 0.80);
        int upY = (int) (size.height * 0.70);
        int downY = (int) (size.height * 0.40);
        WaitOptions waitTime = WaitOptions.waitOptions(Duration.ofMillis(500));
        TouchAction swipe = null;

        switch (thisSwipe) {
            case DOWN:
                swipe = new TouchAction(driver).press(PointOption.point(startX, downY)).waitAction(waitTime).moveTo(PointOption.point(startX, upY));
                break;
            case UP:
                swipe = new TouchAction(driver).press(PointOption.point(startX, upY)).waitAction(waitTime).moveTo(PointOption.point(startX, downY));
                break;
            case LEFT:
                swipe = new TouchAction(driver).press(PointOption.point(righX, startY)).waitAction(waitTime).moveTo(PointOption.point(leftX, startY));
                break;
            case RIGHT:
                swipe = new TouchAction(driver).press(PointOption.point(leftX, startY)).waitAction(waitTime).moveTo(PointOption.point(righX, startY));
                break;
        }
        swipe.release().perform();
        DriverSetup.logMessage("Swipe window completed");
    }

    /**
     * @param driver
     * @param locator
     * @param thisSwipe
     * @throws Exception
     */
    public static void elementSwipe(AppiumDriver<MobileElement> driver, String locator, swipeDirection thisSwipe) throws Exception {
        MobileElement thisEle = driver.findElement(GetUIElements.getProperties(locator));
        Dimension size = thisEle.getSize();
        int leftX = (int) (size.width * 0.10);
        int rightX = (int) (size.width * 0.90);
        int startX = size.width / 2;
        int startY = size.height / 2;
        WaitOptions waitTime = WaitOptions.waitOptions(Duration.ofMillis(500));
        TouchAction swipe = null;

        switch (thisSwipe) {
            case UP:
                swipe = new TouchAction(driver).press(ElementOption.element(thisEle, startX, startY)).waitAction(waitTime).
                        moveTo(ElementOption.element(thisEle, startX, -200));
                break;
            case DOWN:
                swipe = new TouchAction(driver).press(ElementOption.element(thisEle, startX, startY)).waitAction(waitTime).
                        moveTo(ElementOption.element(thisEle, startX, 200));
                break;
            case LEFT:
                swipe = new TouchAction(driver).press(ElementOption.
                        element(thisEle, rightX, startY)).waitAction(waitTime).moveTo(ElementOption.element(thisEle, leftX, 0));
                break;
            case RIGHT:
                swipe = new TouchAction(driver).press(ElementOption.element(thisEle, leftX, startY)).waitAction(waitTime).
                        moveTo(ElementOption.element(thisEle, rightX, 0));
                break;
        }
        swipe.release().perform();
    }

    /**
     * Clears already present text and types the value
     *
     * @param driver
     * @param locator
     * @param setValue
     * @throws Exception
     */
    public static void setEditValue(AppiumDriver<MobileElement> driver, String locator, String setValue) throws Exception {
        MobileElement thisEle = null;
        try {
            thisEle = Helper.fetchElement(driver, locator);
            try {
                thisEle.clear(); // Some fields cannot be cleared, so clear on empty field can cause exception
            } catch (Exception e) {
            }
            if (setValue != null) {
                thisEle.setValue(setValue);
            }
            DriverSetup.logMessage("Set Edit Value on Locator : " + locator + " to :: " + setValue);
        } catch (Exception e) {
            DriverSetup.logMessage("Unable to setValue on Element with Locator : " + locator + " to value :: " + setValue);
            throw e;
        }
    }

    /**
     * Press Enter key from KeyBoard
     *
     * @param driver
     * @throws Exception
     */
    public static void EnterEvent(AndroidDriver<MobileElement> driver) throws Exception {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    /**
     * Press Back key from KeyBoard
     *
     * @param driver
     * @throws Exception
     */
    public static void backEvent(AndroidDriver<MobileElement> driver) throws Exception {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }
}
