package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.support.*;

import java.util.concurrent.*;

import static com.vars.consts.*;

public class Socials extends Common{

    protected Logger logger;

    public Socials(AppiumDriver<MobileElement> driver)  {
        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout, TimeUnit.SECONDS), this);
    }

    public void share_Achievement(String device) {

        swipeDown();

        logger.info(device + ": Sharing achievement");

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]").click();
        driver.findElementById("ru.averia.tracker:id/iv_img").clear();
        driver.findElementById("ru.averia.tracker:id/bt_share").click();
        driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        driver.findElementById("ru.averia.tracker:id/tv_next").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.widget.Button").click();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.findElementById("ru.averia.tracker:id/iv_back").click();

    }

}
