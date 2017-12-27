package Exploring;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class CalculatorTests {

    private Logger logger;

    private WebDriver driver;

    @BeforeTest
    public void SetUp() throws Exception {
        /* appium setup */
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "Asus");
        capabilities.setCapability("browserName", "Android");
        capabilities.setCapability("platformVersion", "5.0.2");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appPackage", "ru.averia.collars.stg");
        capabilities.setCapability("appActivity", "ru.averia.collars.ui.activities.SplashActivity");
        //capabilities.setCapability("appPackage", "com.google.android.calculator");
        //capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        /* selenium driver setup */
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        /* log4j setup */
        logger = Logger.getLogger("AndroidTestLogger");





    }

    @AfterTest
    public void TearDown() {
        driver.quit();
    }


    @Test
    public void CalculatorTestStartup() {
        logger.info("First Test Start");
        Assert.assertEquals("del", driver.findElement(By.id("com.google.android.calculator:id/del")).getText());


    }

    @Test(dependsOnMethods = "CalculatorTestUI")
    public void CalculatorTestEnd() {
        Assert.assertEquals("clr", driver.findElement(By.id("com.google.android.calculator:id/clr")).getText());
    }

    @Test(dependsOnMethods = "CalculatorTestStartup")
    public void CalculatorTestUI() {
        logger.info("Second Test Start");
        int count = 1;
        int random1;
        int random2;
        int random3;


        while (count < 1001) {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            try {
                driver.findElement(By.id("com.google.android.calculator:id/del")).click();
            } catch (NoSuchElementException e){
                driver.findElement(By.id("com.google.android.calculator:id/clr")).click();
            }

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            if (driver.findElement(By.id("com.google.android.calculator:id/del")).isEnabled()) {
//                driver.findElement(By.id("com.google.android.calculator:id/del")).click();
//            } else driver.findElement(By.id("com.google.android.calculator:id/clr")).click();
            random1 = (int)(Math.random()*9);
            random2 = (int)(Math.random()*9);
            random3 = (int)(Math.random()*9);
            driver.findElement(By.id("com.google.android.calculator:id/digit_"+random1)).click();
            driver.findElement(By.id("com.google.android.calculator:id/digit_"+random2)).click();
            driver.findElement(By.id("com.google.android.calculator:id/digit_"+random3)).click();

            Random coin = new Random();
            int toss;
            toss = coin.nextInt(2);

            switch(toss){
                case 0 : driver.findElement(By.id("com.google.android.calculator:id/op_div")).click(); break;
                case 1 : driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click(); break;
                case 2 : driver.findElement(By.id("com.google.android.calculator:id/op_sub")).click(); break;
                case 3 : driver.findElement(By.id("com.google.android.calculator:id/op_add")).click(); break;
            }

            random1 = (int)(Math.random()*9);
            random2 = (int)(Math.random()*9);
            random3 = (int)(Math.random()*9);
            driver.findElement(By.id("com.google.android.calculator:id/digit_"+random1)).click();
            driver.findElement(By.id("com.google.android.calculator:id/digit_"+random2)).click();
            driver.findElement(By.id("com.google.android.calculator:id/digit_"+random3)).click();

            driver.findElement(By.id("com.google.android.calculator:id/eq")).click();

            String result = driver.findElement(By.xpath("//android.widget.TextView[@index='0']")).getText();


            logger.debug(" - - - -");
            logger.debug("Result is : " + result);
            logger.debug("Count is : " + count);
            System.out.println("- - - -");
            System.out.println("Result is : " + result);
            System.out.println("Count is : " + count);

            count++;

        }

    }
}



