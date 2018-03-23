package com.tests;

/* Smoke Tests */

import com.methods.*;
import com.vars.*;
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import org.apache.log4j.*;
import org.openqa.selenium.remote.*;
import org.testng.*;
import org.testng.annotations.*;

import java.net.*;
import java.util.concurrent.*;

public class Smoke {

    Logger logger = Logger.getLogger("AndroidTestLogger");

    String port;
    public String device;
    public String testName;

    public Start_screen start;
    public com.utils.Screenshot screenshot;
    public com.methods.Common common;
    static AppiumDriver<MobileElement> driver;

    DesiredCapabilities caps = new DesiredCapabilities();

    @Parameters({"server_port","device"})
    public Smoke(@Optional("4731") String port, @Optional("default") String device)
    {
        this.port = port;
        this.device = device;
    }

    public void StartUp()
    {

        caps.setCapability("deviceName", device);
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "ru.averia.tracker");
        caps.setCapability("appActivity", "ru.averia.tracker.ui.activities.SplashActivity");
        caps.setCapability("APP", consts.Timeout);

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps);
            //Thread.sleep(1000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Adding all needed methods and utils
        start = new Start_screen(driver);
        screenshot = new com.utils.Screenshot(driver);

        driver.manage().timeouts().implicitlyWait(consts.Timeout, TimeUnit.SECONDS);

    }

    public void Exit() {

        driver.quit();

    }

    @BeforeTest(alwaysRun = true)
    void BeforeSuite()
    {

        logger.info("-----");
        logger.info(device+": "+"Initial Settings and App Startup");

        StartUp();

        logger.info(device+": "+"Settings Applied");

    }

    @AfterTest
    void AfterSuite() {
        Exit();
    }

    @AfterMethod
    void afterMethod(ITestResult result)
    {

        testName = result.getName();

        try
        {
            if(result.getStatus() == ITestResult.SUCCESS)
            {
                logger.info(device + ": Passed " + testName);
            }

            else if(result.getStatus() == ITestResult.FAILURE)
            {
                logger.info(device + ": Failed " + testName);
                screenshot.captureScreenShots(device, testName);
            }

            else if(result.getStatus() == ITestResult.SKIP )
            {
                logger.info(device + ": Skiped " + testName);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    void SplashScreen()
    {

        start.SplashScreen();

    }

    @Test(dependsOnMethods = "SplashScreen")
    void Register()
    {

        start.Register(device);

    }

    @Test(dependsOnMethods = "Register")
    void Login() {

        Exit();
        StartUp();
        start.SplashScreen();
        start.Login(device);

    }

}
