package com.tests;

import com.methods.*;
import com.utils.*;
import com.var.*;
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import org.apache.log4j.*;
import org.openqa.selenium.remote.*;
import org.testng.*;
import org.testng.annotations.*;

import java.net.*;
import java.util.concurrent.*;

import static com.var.vars.petname;
import static com.var.vars.temp_petname;

public class FullBuildTest {

    public Logger logger = Logger.getLogger("AndroidTestLogger");

    private String port;
    public String device;

    private Start_screen start;
    private Screenshot screenshot;
    private GetDeviceInfo deviceinfo;
    public Common common;
    private Profile_screen profile_screen;
    private Main_screen main_screen;
    private Pet_screen pet_screen;
    public Socials social;
    private Map_screen map_screen;
    protected static AppiumDriver<MobileElement> driver;

    private DesiredCapabilities caps = new DesiredCapabilities();

    @Parameters({"server_port","device"})
    public FullBuildTest(@Optional("4731") String port, @Optional("default") String device)
    {
        this.port = port;
        this.device = device;
    }

    private void StartUp()
    {

        logger.info(device + ": Starting app");

        //Adding all Caps
        caps.setCapability("deviceName", device);
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "ru.averia.tracker");
        caps.setCapability("appActivity", "ru.averia.tracker.ui.activities.SplashActivity");
        caps.setCapability("app", consts.app_path_mac);
        //caps.setCapability("udid",consts.phone_lg);
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("gpsEnabled", true);

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps);
            //Thread.sleep(1000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Adding all needed methods and utils
        start = new Start_screen(driver);
        screenshot = new Screenshot(driver);
        deviceinfo = new GetDeviceInfo(driver);
        common = new Common(driver);
        profile_screen = new Profile_screen(driver);
        main_screen = new Main_screen(driver);
        pet_screen = new Pet_screen(driver);
        map_screen = new Map_screen(driver);
        social = new Socials(driver);


        //All done, start driver
        driver.manage().timeouts().implicitlyWait(consts.Timeout, TimeUnit.SECONDS);

        logger.info(device + ": App launched");

    }

    private void Exit() {

        logger.info(device + ": Closing app");
        driver.quit();

    }

    @BeforeTest(alwaysRun = true)
    void BeforeSuite()
    {

        logger.info("-----");
        logger.info(device+": "+"Initial Settings and App Startup");

        StartUp();

        logger.info(device+": "+"Settings Applied");

        deviceinfo.getDeviceInfo(device);

    }

    @AfterTest
    void AfterSuite() {
        Exit();
    }

    @AfterMethod(alwaysRun = true)
    void afterMethod(ITestResult result)
    {

        String testName = result.getName();

        try
        {
            if(result.getStatus() == ITestResult.SUCCESS)
            {
                logger.info("> " + device + ": Passed " + testName);
            }

            else if(result.getStatus() == ITestResult.FAILURE)
            {
                logger.info("> " + device + ": Failed " + testName);
                screenshot.captureScreenShots(device, testName);
            }

            else if(result.getStatus() == ITestResult.SKIP )
            {
                logger.info("> " + device + ": Skiped " + testName);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
        void Register()
        {

            start.SplashScreen();
            start.Register(device);

        }

    @Test(dependsOnMethods = "Register")
        void Login()
        {

            Exit();
            StartUp();
            start.SplashScreen();
            start.Login(device);

        }

    @Test(dependsOnMethods = "Login")
        void AddPet()
        {

            pet_screen.addFirstPet(device, petname);
            common.gotoProfileScreen(device);
            pet_screen.petEdit(device);

        }

    @Test(dependsOnMethods = "AddPet")
        void MainActivity()
        {

            common.ScreensShuffle();
            common.gotoMainScreen(device);

        }

    @Test(dependsOnMethods = "AddPet")
        void UserProfile()
        {

            common.gotoProfileScreen(device);
            profile_screen.userProfileEdit(device);
            common.gotoMainScreen(device);

        }

    @Test(dependsOnMethods = "UserProfile")
    void PetProfile()
    {

        common.gotoProfileScreen(device);
        pet_screen.petEdit(device);

    }


    @Test(dependsOnMethods = "UserProfile")
        void AddDeletePet()
        {
            pet_screen.deletePet(device, temp_petname);

        }

    @Test(dependsOnMethods = "AddDeletePet", alwaysRun = true)
        void Restart(){

            Exit();
            StartUp();

        }

   @Test(dependsOnMethods = "Restart")
        void LoginExistingUser(){

        start.SplashScreen();
        start.Login_old(device);
        main_screen.checkScreen(device);

    }

    @Test(dependsOnMethods = "LoginExistingUser")
    void MainScreenElements()
    {
        common.gotoMainScreen(device);
        main_screen.checkScreen(device);

    }

    @Test(dependsOnMethods = "LoginExistingUser")
        void ChekingStatistic(){

        common.gotoMainScreen(device);
        main_screen.walkStats(device);

    }

/*
    @Test(dependsOnMethods = "LoginExistingUser")
    void Achievements(){

        common.gotoMainScreen(device);
        social.share_Achievement(device);


    }
*/

    @Test(dependsOnMethods = "LoginExistingUser")
    void SafeZone(){

        common.gotoMapScreen(device);
        common.gotoProfileScreen(device);
        profile_screen.fromProfileToPet(device, "Ipkez");
        pet_screen.fromPetToSafeZone(device);
        map_screen.addSafeZone(device);

    }

    @Test(dependsOnMethods = "LoginExistingUser")
    void CheckNotifications() {

        common.openNotifications(device);
        common.checkNotifications(device);
        common.gotoMainScreen(device);

    }

}
