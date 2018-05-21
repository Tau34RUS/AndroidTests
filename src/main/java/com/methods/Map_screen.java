package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.support.*;

import java.util.concurrent.*;

import static com.vars.consts.*;

public class Map_screen extends Common{

    protected Logger logger;

    public Map_screen(AppiumDriver<MobileElement> driver)  {
        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout, TimeUnit.SECONDS), this);
    }

    public void mapActions(String device) {

        logger.info(device + ": Checking map screen");
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        driver.findElementById("ru.averia.tracker:id/ib_pet_position").click();
        driver.findElementById("ru.averia.tracker:id/ib_self_position").click();
        driver.findElementById("ru.averia.tracker:id/ib_other_pets").click();

    }

    public void addSafeZone(String device) { //should be on other screen, but it is here

        logger.info(device + ": Adding test safe zone");
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.LinearLayout[1]").click();
        swipeUp();
        driver.findElementById("ru.averia.tracker:id/tv_safezones").click();
        driver.findElementById("ru.averia.tracker:id/fab_add").click();
        driver.findElementById("ru.averia.tracker:id/et_name").sendKeys("test safe zone (delete me)");
        driver.findElementById("ru.averia.tracker:id/iv_set_name").click();
        logger.info(device + ": Saving test safe zone");
        driver.findElementById("ru.averia.tracker:id/iv_action").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton").click();
        sleep(2);
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]").click();
        sleep(2);
        driver.findElementByAccessibilityId("Другие варианты").click();
        sleep(2);
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]").click();
        sleep(2);
        driver.findElementById("ru.averia.tracker:id/md_buttonDefaultPositive").click();
        logger.info(device + ": Deleting test safe zone");
        sleep(2);
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton").click();
        sleep(2);
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.widget.ImageButton").click();


    }

}