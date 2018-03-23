package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.support.*;
import org.testng.*;

import java.util.concurrent.*;

import static com.vars.consts.*;

public class Pet_screen extends Common{

    protected Logger logger;

    public Pet_screen(AppiumDriver<MobileElement> driver)  {
        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout, TimeUnit.SECONDS), this);
    }

    public void addCollar() {

        Assert.assertEquals("Добавьте ошейник" ,driver.findElementById("ru.averia.tracker:id/tv_add_collar_title1").getText());
        driver.findElementById("ru.averia.tracker:id/tv_add_collar").click();

        driver.findElementById("ru.averia.tracker:id/bt_search").click();
        try{driver.findElementById("ru.averia.tracker:id/container_list").clear();}
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No BLE Devices List");
        }

        try{driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[2]/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]").clear();}
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No BLE Devices Page");
        }

        driver.findElementById("ru.averia.tracker:id/iv_back").click();

    }

    public void petEdit() {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.Button").click();

        driver.findElementById("ru.averia.tracker:id/et_name").sendKeys("1");

        //HideKeyboard();

        swipeUp();

        driver.findElementById("ru.averia.tracker:id/et_weight").sendKeys("33");

        driver.findElementById("ru.averia.tracker:id/et_height").sendKeys("33");

        driver.findElementById("ru.averia.tracker:id/iv_save").click();

        //HideKeyboard();

    }

}