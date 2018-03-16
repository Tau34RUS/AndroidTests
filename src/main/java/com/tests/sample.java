package com.tests;

/* Sample for tests */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class sample {

    String port;
    String device;

    private com.methods.start_screens start;
    private static AppiumDriver<MobileElement> driver;

    DesiredCapabilities capabilities = new DesiredCapabilities();

    @Parameters({"server_port", "device"})
    public sample(String port, String device) {
        this.port = port;
        this.device = device;
    }

    void ParallelSetup() {

        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "ru.averia.tracker");
        capabilities.setCapability("appActivity", "ru.averia.tracker.ui.activities.SplashActivity");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
            //Thread.sleep(1000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        start = new com.methods.start_screens(driver);

    }

    public void Quit() {
        driver.quit();
    }

    @BeforeTest(alwaysRun = true)
    void BeforeSuite() {
        Logger logger = Logger.getLogger("AndroidTestLogger");

        logger.info("-----");
        logger.info(device+": "+"Initial Settings and App Startup");

        ParallelSetup();

        logger.info(device+": "+"Settings Applied");
    }

    @AfterTest
    void AfterSuite() {
        Quit();
    }

    @Test
    void SplashScreen() {
        start.SplashScreen();
    }

    @Test(dependsOnMethods = "SplashScreen")
    void Register() {

        start.Register();
    }
}
