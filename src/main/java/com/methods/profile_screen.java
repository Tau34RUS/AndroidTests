package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;
import java.util.concurrent.*;

import static com.vars.consts.*;
import static com.vars.vars.*;

public class profile_screen extends common{

    protected Logger logger;

    public profile_screen(AppiumDriver<MobileElement> driver)  {
        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout, TimeUnit.SECONDS), this);
    }

    public void UserProfile() {

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
                logger.info("Swipe up");
                SwipeUp();

                logger.info("Fill phone number");
                Random login = new Random();

                String alphabet = "1234567890";
                phonenumber = "";
                for (int i = 0; i < 11; i++) phonenumber += login.nextInt(alphabet.length());
                driver.findElementById("ru.averia.tracker:id/et_phone").sendKeys(phonenumber);

                logger.info("Swipe down");
                SwipeDown();

                driver.findElementById("ru.averia.tracker:id/container_avatar").click();

                PhonePhoto();

                driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();
                Sleep(5);
                driver.findElementById("ru.averia.tracker:id/iv_save").click();
                Sleep(5);
                logger.info("Saving user profile changes");
                try {driver.findElementById("ru.averia.tracker:id/iv_save").click();}
                catch (org.openqa.selenium.NoSuchElementException e) {logger.info("Already saved?");}
                break;

        }

    }
    
}