package mmt.automation.common;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TestListener extends TestListenerAdapter {

    /**
     * TO Capture ScreenShot
     *
     * @param result
     */
    public void captureScreenShot(ITestResult result) throws AWTException {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Rectangle rectangle = new Rectangle(0, 0, screenSize.width, screenSize.height);
        Robot robot = new Robot();

        try {
            if (result != null) {
                DriverSetup ti = (DriverSetup) result.getInstance();
                if (ti != null) {
                    String myDevice = ti.deviceName;
                    String myPlatform = ti.platformName;
                    String myVersion = ti.platformVersion;
                    String myTestNum = result.getName();
                    String fileName = (myPlatform + "_" + myDevice + "_" + myVersion + "_" + myTestNum + "_" + Helper.getDate()) + ".png";
                    fileName = fileName.replaceAll("[^a-zA-Z0-9\\\\s\\\\.]", "");
                    result.setAttribute("screenshotLoc", fileName);


                    try {
                        BufferedImage screenshotImage = robot.createScreenCapture(rectangle);
                        File screenshotFile = new File("file-screenshots" + File.separator + fileName);
                        ImageIO.write(screenshotImage, "png", screenshotFile);
                    } catch (Exception e) {
                        DriverSetup.logMessage(e.getMessage());
                    }

                    Reporter.log("<a href=" + fileName + ">Click to open failure screenshot</a>");
                } else {
                    DriverSetup.logMessage("Driver not exist. Screenshot cannot be taken !!");
                }
            } else {
                DriverSetup.logMessage("Driver not exist. Screenshot cannot be taken !!");
            }
        } catch (Exception e) {
            DriverSetup.logMessage("Unable to capture screenshot due to exception: " + e);
        }
    }

    /**
     * Capture screenshot on a test failure.
     *
     * @param result
     */
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            captureScreenShot(result);
        }catch (AWTException e) {
            DriverSetup.logMessage("Unable to capture screenshot due to exception: " + e);
        }
    }

    /**
     * Capture screenshot for a failure during app configuration.
     *
     * @param result
     */
    @Override
    public void onConfigurationFailure(ITestResult result) {
        System.out.println("In Configuration failure");
        try {
            captureScreenShot(result);
        }catch (AWTException e) {
            DriverSetup.logMessage("Unable to capture screenshot due to exception: " + e);
        }
    }

    /**
     * @param result
     */
    @Override
    public void onConfigurationSkip(ITestResult result) {
        System.out.println("In Configuration failure");
        try {
            captureScreenShot(result);
        }catch (AWTException e) {
            DriverSetup.logMessage("Unable to capture screenshot due to exception: " + e);
        }
    }
}
