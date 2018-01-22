package AppiumBased;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AllInOne {

    private static Logger logger;

    private final Methods App = new Methods();

    @BeforeTest
    public void Before() throws Exception {

        /* log4j setup */
        logger = Logger.getLogger("AndroidTestLogger");

        logger.info("-----");
        logger.info("Initial Settings and App Startup");
        App.SetUp();
        logger.info("Settings Applied");

    }

    @AfterTest
    public void After() {
        logger.info("-----");
        logger.info("Userlogin: " + Variables.userlogin);
        logger.info("Userpass:  " + Variables.userpass);

        logger.info("-----");
        logger.info("Closing Application");
        App.Quit();

    }

    @AfterMethod
    public void AfterMethod(ITestResult result) throws IOException {
        if (result.getStatus()==2) {
            logger.info("Test Failed!");
            logger.info("Taking Screenshot...");
            App.captureScreenShots();
        }
        logger.warn("Result is: " + result.getStatus());
    }

    @Test
    public void AndroidTestRegister() {

        logger.info("-----");
        logger.info("Starting AppiumBased Tests");
        logger.info("-   -");

        App.SplashScreen();

        logger.info("Registering a user");

        App.Register();

    }

    @Test(dependsOnMethods = "AndroidTestRegister")
    public void AndroidTestLogin() throws Exception{

        logger.info("- - -");
        logger.info("Restarting App");
        App.Restart();
        logger.info("-   -");
        logger.info("Splash Screen");


        App.SplashScreen();

        logger.info("-   -");
        logger.info("Logging with a user");

        App.Login();

    }

    @Test(dependsOnMethods = "AndroidTestLogin")
    public void AndroidTestAddPet() {

        logger.info("- - -");
        logger.info("Adding a pet");
       // App.AddPet();

    }

}
