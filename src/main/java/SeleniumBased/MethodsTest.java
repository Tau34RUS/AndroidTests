package SeleniumBased;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MethodsTest {

    private static Logger logger;

    private final Methods App = new Methods();

    @BeforeTest
    public void Before() throws Exception {

        /* log4j setup */
        logger = Logger.getLogger("AndroidTestLogger");


        logger.info("-----");
        logger.info("Initial Settings and App Startup");
        App.SetUp();
        logger.info("Screen size: " + Variables.screensize);
        logger.info("Settings Applied");

    }

    @AfterTest
    public void After() {
        logger.info("-----");
        logger.info("Userlogin: " + Variables.userlogin);
        logger.info("Userpass:  " + Variables.userpass);

        logger.info("-----");
        logger.info("Closing Application");
        //App.Quit();
    }

    @Test
    public void AndroidTestLogin() throws Exception {
        int a;
        int i=1;
        a = 1;
        while (a<10) {
            logger.info("SplashScreen");
            App.SplashScreen();
            logger.info("Registering");
            App.Register();
            logger.info("AppRestart");
            App.Restart();
            logger.info("SplashScreen");
            App.SplashScreen();
            logger.info("Login");
            App.Login();
            logger.info("Adding new pet");
            //App.HorizontalScrollL2R();
            App.AddPet();
            logger.info("Restarting");
            App.Restart();
            logger.info("Iteration: "+i);
            i++;
        }

    }

}
