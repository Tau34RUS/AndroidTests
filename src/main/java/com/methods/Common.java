package com.methods;

import io.appium.java_client.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.testng.*;

import java.util.*;

import static com.vars.consts.*;
import static com.vars.vars.*;

public class Common {

    public AppiumDriver driver;

    Logger logger = Logger.getLogger(Common.class);

    public Common(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void androidAllowAccess() {
        try {
            driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No Permissions requested");
        }

    }

    public void sleep (Integer seconds) {

        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void swipeUp() {

        int starty = (int) (screensize.height * 0.50);
        int endy = (int) (screensize.height * 0.20);
        int startx = screensize.width / 2;
        driver.swipe(startx,starty,startx,endy,300);
        driver.swipe(startx,starty,startx,endy,300);
        sleep(1);
    }

    public void swipeDown() {

        int starty = (int) (screensize.height * 0.20);
        int endy = (int) (screensize.height * 0.70);
        int startx = screensize.width / 2;
        driver.swipe(startx,starty,startx,endy,300);
        driver.swipe(startx,starty,startx,endy,300);
        sleep(1);
    }

    public void gotoMainScreen() {

        driver.findElementById("ru.averia.tracker:id/maim_menu_action_pet").click();

    }

    public void gotoMapScreen() {

        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();

    }

    public void gotoProfileScreen() {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();

    }

    public void ScreensShuffle() {
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_pet").click();
    }

    public void phonePhoto() {
        androidAllowAccess();
        switch (devicename) {

            case (phone_sony_xperia):
                //fkn experia does not want to focus an image
                logger.info("Sony Xperia X Perfomance photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]").click();
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout").click();

                break;

            case (phone_asuszenpad):
                logger.info("Asus Zenpad photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.ListView/android.widget.LinearLayout[1]").click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (phone_htc):
                List<MobileElement> choiseslist_htc = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_htc);
                choiseslist_htc.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (phone_samsung_j1):
                logger.info("Samsung J1 photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/com.android.internal.widget.ResolverDrawerLayout/android.widget.GridView/android.widget.LinearLayout[1]/android.widget.ImageView").click();
                driver.findElementByAccessibilityId("MENUID_SHUTTER").click();
                driver.findElementById("com.sec.android.app.camera:id/okay").click();

                break;
            case (phone_samsung_edge):
                logger.info("Samsung Edge photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/com.android.internal.widget.ViewPager/android.widget.LinearLayout/android.widget.GridView/android.widget.LinearLayout[1]").click();
                sleep(5);
                driver.findElementByXPath("(//GLButton[@content-desc=\"NONE\"])[3]").click();
                driver.findElementById("com.sec.android.app.camera:id/okay").click();

                break;
            case (phone_lg):
                logger.info("LG photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout/android.widget.LinearLayout[2]").click();
                sleep(2);
                (new TouchAction(driver)).tap(109, 203).perform();
                sleep(2);
                (new TouchAction(driver)).tap(109, 203).perform();
                sleep(1);

                break;
            case (phone_meizu_n5):
                List<MobileElement> choiseslist_meizu_n5 = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_meizu_n5);
                choiseslist_meizu_n5.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (phone_xiomi_x4_note):
                logger.info("Xiomi X4 Note photo sequence applied");
                List<MobileElement> choiseslist_xiomi_x4_note = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_xiomi_x4_note);
                choiseslist_xiomi_x4_note.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (phone_honor_c3):
                logger.info("Honor 3C photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.view.ViewPager/android.widget.GridView/android.widget.LinearLayout[1]").click();
                driver.findElementByAccessibilityId("Сфотографировать").click();
                driver.findElementByAccessibilityId("Готово").click();

                break;
            case (phone_nexus_5):
                logger.info("Nexus 5 photo sequence applied");
                androidAllowAccess();
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout").click();
                driver.findElementByAccessibilityId("Затвор").click();
                driver.findElementByAccessibilityId("Готово").click();

                break;
            default:
                logger.info("Default photo action applied");
                sleep(2);
                androidAllowAccess();
                List<MobileElement> choiseslist = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist);
                choiseslist.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
        }
    }

    public void AddPet (){

        driver.findElement(By.id("ru.averia.tracker:id/maim_menu_action_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_description_large")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/et_name")).sendKeys(petname);

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        driver.findElement(By.id("ru.averia.tracker:id/iv_pet_ava")).click();

        phonePhoto();

        driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        //Shitty Magic
        (new TouchAction(driver)).tap(462, 710).perform();
        (new TouchAction(driver)).tap(400, 400).perform();

        try {
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[4]").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.warn("No List Element 'Breed' Found!");
        }

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Возраст и дата рождения", driver.findElement(By.id("ru.averia.tracker:id/tv_cap")).getText());

        driver.findElementById("ru.averia.tracker:id/et_age").sendKeys(birthyear);

        driver.findElementById("ru.averia.tracker:id/et_month").click();

        try {
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.warn("Selecting Month Error!");
        }
        try {
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[5]").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.warn("Selecting Month Error!");
        }

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        driver.findElement(By.id("ru.averia.tracker:id/til_weight")).sendKeys(petweight);

        driver.findElement(By.id("ru.averia.tracker:id/et_height")).sendKeys(petheight);

        driver.navigate().back();

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Добавьте ошейник", driver.findElement(By.id("ru.averia.tracker:id/tv_add_collar_title1")).getText());
        Assert.assertEquals(petname, driver.findElement(By.id("ru.averia.tracker:id/tv_pet_name")).getText());

    }

}
