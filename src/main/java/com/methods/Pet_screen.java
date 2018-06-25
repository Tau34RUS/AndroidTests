package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.testng.*;

import java.util.concurrent.*;

import static com.var.consts.*;
import static com.var.vars.*;

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

    public void petEdit(String device) {
            swipeUp();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]").click();
            driver.findElementById("ru.averia.tracker:id/bt_edit_profile").click();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton").click();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.widget.ImageButton").click();
    }

    public boolean checkTempPetExist (String device, String name){

        logger.info(device + ": Check Temp Pet Exist");

        gotoProfileScreen(device);

        swipeUpToElementId("ru.averia.tracker:id/container_add_pet");

        if (isElementPresent(By.xpath("//android.widget.TextView[@text='"+temp_petname+"']"))) {

            sleep (5);
            return true;
        }
        else {
            return false;
        }
    }

    public void deletePet (String device, String name){

        logger.info(device + ": Deleting Temp Pet");

        if (!checkTempPetExist(device, name)){
           addPetFromProfile(device, name);
        }
        else {
            logger.info(device + ": Temp Pet already exist!");
        }
        //checking for existing temp pet and deleting
        swipeUp();

        driver.findElement(By.xpath("//android.widget.TextView[@text='"+name+"']")).click();
        swipeUpToElementId("ru.averia.tracker:id/tv_remove_pet");
        driver.findElementById("ru.averia.tracker:id/tv_remove_pet").click();
        driver.findElementById("ru.averia.tracker:id/md_buttonDefaultPositive").click();
        sleep (5);
        if (isElementPresent(By.xpath("//android.widget.TextView[@text='"+name+"']"))) { logger.error(device + ": Temp Pet Still Present!"); }

    }

    public void addPet(String device, String name){

        logger.info(device + ": Adding Pet");

        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());

        driver.findElement(By.id("ru.averia.tracker:id/et_name")).sendKeys(name);

        driver.findElement(By.id("ru.averia.tracker:id/iv_pet_ava")).click();

        phonePhoto();

        driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Порода", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        driver.navigate().back();

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        driver.findElement(By.xpath("//*[@text='Австралийская келпи']")).click();
        driver.findElementById("ru.averia.tracker:id/bt_next").click();

        driver.findElementById("ru.averia.tracker:id/til_age").sendKeys(petage);
        driver.navigate().back();
        driver.findElementById("ru.averia.tracker:id/bt_next").click();

        driver.findElementById("ru.averia.tracker:id/til_weight").sendKeys(petweight);

        driver.findElementById("ru.averia.tracker:id/til_height").sendKeys(petheight);

        driver.navigate().back();

        driver.findElementById("ru.averia.tracker:id/bt_next").click();

        sleep(8);
    }

    public void addFirstPet(String device, String name){

        logger.info(device + ": Adding First Pet");

        driver.findElement(By.id("ru.averia.tracker:id/maim_menu_action_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_description_large")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).click();

        addPet(device,name);

    }

    public void addPetFromProfile(String device, String name){

        logger.info(device + ": Adding Pet from Profile Screen");

        gotoProfileScreen(device);
        swipeUpToElementId("ru.averia.tracker:id/container_add_pet");
        driver.findElementById("ru.averia.tracker:id/container_add_pet").click();

        addPet(device,name);

    }

    public void addPetFromMain(String device, String name) {

        logger.info(device + ": Adding Pet from Main Screen");

        gotoMainScreen(device);

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup").click();
        driver.findElementById("ru.averia.tracker:id/tv_add_pet").click();

        addPet(device,name);
    }

    public void fromPetToSafeZone(String device){

        logger.info(device + ": Go to the Safe zone list");

        swipeUp();
        driver.findElementById("ru.averia.tracker:id/tv_safezones").click();

    }

}