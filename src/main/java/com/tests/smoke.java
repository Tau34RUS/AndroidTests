package com.tests;

/* Sample for tests */

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import org.apache.log4j.*;
import org.openqa.selenium.remote.*;
import org.testng.*;
import org.testng.annotations.*;

import java.net.*;
import java.util.concurrent.*;

public class smoke {

    Logger logger = Logger.getLogger("AndroidTestLogger");

    String port;
    public String device;
    public String testName;

    private com.methods.start_screens start;
    private com.utils.screenshot screenshot;
    private static AppiumDriver<MobileElement> driver;

    DesiredCapabilities capabilities = new DesiredCapabilities();



    @Parameters({"server_port","device"})
    public smoke(@Optional("4731") String port, @Optional("default") String device)
    {
        this.port = port;
        this.device = device;
    }

    @BeforeTest(alwaysRun = true)
    void BeforeSuite()
    {

        logger.info("-----");

        StartUp();

        logger.info(device+": "+"Initial Settings and App Startup");
        logger.info(device+": "+"Settings Applied");

    }

    private void StartUp()
    {

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
        screenshot = new com.utils.screenshot(driver);

    }

    private void Exit() {
        driver.quit();
    }

    @AfterTest
    void AfterSuite() {
        driver.quit();
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
        start.Login(device);

    }

}
