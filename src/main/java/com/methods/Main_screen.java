package com.methods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Main_screen extends Common {



    protected Logger logger;

    public Main_screen(AppiumDriver<MobileElement> driver)  {

        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, com.var.consts.Timeout, TimeUnit.SECONDS), this);
    }

    public void checkScreen(String device) {

        logger.info(device + ": Checking Main screen objects:");

        logger.info(device + ": - Top bar elements");
        driver.findElementById("ru.averia.tracker:id/tv_pet_name").clear();
        driver.findElementById("ru.averia.tracker:id/iv_ava").click();
        driver.findElementById("ru.averia.tracker:id/tv_battery_level").clear();
        driver.findElementById("ru.averia.tracker:id/v_battery_level").clear();
        driver.findElementById("ru.averia.tracker:id/container_battery_level").clear();
        driver.findElementById("ru.averia.tracker:id/iv_bluetooth").clear();
        //driver.findElementById("ru.averia.tracker:id/tv_date").clear(); --current date
        //driver.findElementById("android:id/button1").click();
        //driver.findElementById("ru.averia.tracker:id/iv_prev").click(); --prevoise date
        //driver.findElementById("ru.averia.tracker:id/iv_next").click(); --next date
        System.out.println("Device mode: "+ driver.findElementByAccessibilityId("StatusCard").getText());

        logger.info(device + ": - Stats");
        driver.findElementByAccessibilityId("StatsCard").click();
        assertEquals ("Статистика", driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView").getText());
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton").click();

        logger.info(device + ": - Tracks");
        swipeUp();
        driver.findElementByAccessibilityId("LastTrackCard").click(); // recent walks
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[2]").click(); //last walk
        driver.findElementById("ru.averia.tracker:id/iv_ava").click(); //return
        driver.findElementById("ru.averia.tracker:id/iv_ava").click(); //return

        logger.info(device + ": - Target");
        driver.findElementByAccessibilityId("SetTargetCard").click();
        assertEquals("Настроить цель", driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView").getText());
        driver.findElementById("ru.averia.tracker:id/bt_set").click();
        /*
        logger.info(device + ": - Achievements"); -- no Achivement now
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.ImageView").click();
        logger.info(device + " ");
        driver.findElementById("ru.averia.tracker:id/iv_img").clear();
        logger.info(device + "2");
        driver.findElementById("ru.averia.tracker:id/iv_back").click();*/

    }

    public void walkStats(String device) {

        logger.info(device + ": Checking Main screen stats");
        swipeDown();
        driver.findElementByAccessibilityId("StatsCard").click();
        sleep(5);

        if(isElementPresent(By.id("ru.averia.tracker:id/tv_no_data"))) {logger.error(device + ": no activity for chosen period");}
        else {logger.error(device + ": There are some stats for chosen period");}

        int i=2;
        while (i<=4) {
            String s = String.valueOf(i);
            String xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[" + s + "]";
            driver.findElementByXPath(xpath).click();
            if(isElementPresent(By.id("ru.averia.tracker:id/tv_no_data"))) {logger.error(device + ": no activity for chosen period");}
            else {logger.error(device + ": There are some stats for chosen period");}
            i++;
        }

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageButton").click();

    }


}
