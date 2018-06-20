package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;
import java.util.concurrent.*;

import static com.var.consts.*;
import static com.var.vars.*;

public class Profile_screen extends Common{

    protected Logger logger;

    public Profile_screen(AppiumDriver<MobileElement> driver)  {
        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout, TimeUnit.SECONDS), this);
    }

    public void userProfileView(String device) {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        driver.findElementById("ru.averia.tracker:id/iv_ava").clear();
        driver.findElementById("ru.averia.tracker:id/tv_name").clear();
        driver.findElementById("ru.averia.tracker:id/tv_info").clear();

    }

    public void userProfileEdit(String device) {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        driver.findElementById("ru.averia.tracker:id/iv_ava").clear();
        driver.findElementById("ru.averia.tracker:id/tv_name").clear();
        driver.findElementById("ru.averia.tracker:id/tv_info").clear();

        switch (devicename) {
            case (phone_nexus_5): //damned cyanogen
                break;
            default:

                driver.findElementById("ru.averia.tracker:id/bt_edit_profile").click();
                driver.findElementById("ru.averia.tracker:id/et_last_name").sendKeys("Tester");
                logger.info(device + ": Swipe up");
                swipeUp();

                logger.info(device + ": Fill phone number");
                Random login = new Random();

                String alphabet = "1234567890";
                phonenumber = "";
                for (int i = 0; i < 11; i++) phonenumber += login.nextInt(alphabet.length());
                driver.findElementById("ru.averia.tracker:id/et_phone").sendKeys(phonenumber);

                logger.info(device + ": Swipe down");
                swipeDown();

                driver.findElementById("ru.averia.tracker:id/container_avatar").click();

                phonePhoto();

                driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();
                sleep(5);
                driver.findElementById("ru.averia.tracker:id/iv_save").click();
                sleep(5);
                logger.info(device + ": Saving user profile changes");
                break;

        }

    }

    public void fromProfileToSafeZone(String device){
        logger.info(device + ": Go to the Safe zone list");

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        swipeUpToElementId("ru.averia.tracker:id/iv_collar_attached");
        driver.findElement(By.xpath("//android.widget.TextView[@text='Ipkez']")).click();
        swipeUp();
        driver.findElementById("ru.averia.tracker:id/tv_safezones").click();
    }
    
}