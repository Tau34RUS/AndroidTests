package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.testng.*;

import java.util.concurrent.*;

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
        driver.findElementById("ru.averia.tracker:id/tv_battery").clear();
        driver.findElementById("ru.averia.tracker:id/v_battery_level").clear();
        driver.findElementById("ru.averia.tracker:id/container_battery_level").clear();
        driver.findElementById("ru.averia.tracker:id/tv_date").clear();
        driver.findElementById("android:id/button1").click();
        driver.findElementById("ru.averia.tracker:id/iv_prev").click();
        driver.findElementById("ru.averia.tracker:id/iv_next").click();
        driver.findElementById("ru.averia.tracker:id/tv_status_short").click();
        driver.findElementById("ru.averia.tracker:id/container_step").clear();

        logger.info(device + ": - Stats");
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView").clear();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton").click();
        driver.findElementById("ru.averia.tracker:id/container_run").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView").clear();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton").click();
        driver.findElementById("ru.averia.tracker:id/container_rest").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView").clear();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton").click();
        driver.findElementById("ru.averia.tracker:id/container_calories").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView").clear();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton").click();

        logger.info(device + ": - Achievements");
        swipeUp();
        driver.findElementById("ru.averia.tracker:id/container_goto_all_tracks").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[2]").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ImageButton").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ImageButton").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.ImageView").click();
        driver.findElementById("ru.averia.tracker:id/iv_img").clear();
        driver.findElementById("ru.averia.tracker:id/iv_back").click();

    }

    public void walkStats(String device) {

        logger.info(device + ": Checking Main screen stats");
        swipeDown();
        driver.findElementById("ru.averia.tracker:id/container_step").click();
        sleep(5);
        Assert.assertEquals("Ходьба", driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView").getText());
//        Assert.assertNotEquals("0", driver.findElementById("ru.averia.tracker:id/tv_total").getText());
        if(isElementPresent(By.id("ru.averia.tracker:id/container_no_stats"))) {logger.error(device + ": Stats1");}
        else {logger.error(device + ": No stats1");}
        if(isElementPresent(By.id("ru.averia.tracker:id/tv_error"))) {logger.error(device + ": Stats2");}
        else {logger.error(device + ": No stats2");}
        int i=2;
        while (i<=4){
            String s = String.valueOf(i);
            String xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[" + s + "]";
            driver.findElementByXPath(xpath).click();
            //           Assert.assertNotEquals("0", driver.findElementById("ru.averia.tracker:id/tv_total").getText());
            if(isElementPresent(By.id("ru.averia.tracker:id/container_no_stats"))) {logger.error(device + ": Stats1");}
            else {logger.error(device + ": No stats1");}
            if(isElementPresent(By.id("ru.averia.tracker:id/tv_error"))) {logger.error(device + ": Stats2");}
            else {logger.error(device + ": No stats2");}
            i++;
        }

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton").click();

    }


}
