package com.methods;

import io.appium.java_client.*;
import org.apache.log4j.*;

public class common {

    public AppiumDriver driver;
    Logger logger = Logger.getLogger(common.class);

    public common(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void AndroidAllowAccess() {
        try {
            driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No Permissions requested");
        }

    }

    public void Sleep (Integer seconds) {

        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
