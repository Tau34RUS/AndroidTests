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
            swipeUpToElementId("ru.averia.tracker:id/tv_about");
    }

    public void addPet (String device){

        logger.info(device + ": Adding Pet");

        driver.findElement(By.id("ru.averia.tracker:id/maim_menu_action_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_description_large")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
 //       driver.findElement(By.id("ru.averia.tracker:id/et_name")).click();
        driver.findElement(By.id("ru.averia.tracker:id/et_name")).sendKeys(petname);


        driver.findElement(By.id("ru.averia.tracker:id/iv_pet_ava")).click();

        phonePhoto();

        driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Порода", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
/*
        //Shitty Magic
        (new TouchAction(driver)).tap(462, 710).perform();
        (new TouchAction(driver)).tap(400, 400).perform();

        try {
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[4]").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.warn("No List Element 'Breed' Found!");
        }
*/
        driver.navigate().back();

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        //driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.View[2]").click();

        driver.findElement(By.xpath("//*[@text='Австралийская келпи']")).click();
        driver.findElementById("ru.averia.tracker:id/bt_next").click();

        driver.findElementById("ru.averia.tracker:id/til_age").sendKeys(petage);
        driver.navigate().back();
        driver.findElementById("ru.averia.tracker:id/bt_next").click();

        driver.findElementById("ru.averia.tracker:id/til_weight").sendKeys(petweight);

        driver.findElementById("ru.averia.tracker:id/til_height").sendKeys(petheight);

        driver.navigate().back();

        driver.findElementById("ru.averia.tracker:id/bt_next").click();
    }

    public void addPetProfileScreen (String device){

        logger.info(device + ": Adding Temp Pet");

        swipeUpToElementId("ru.averia.tracker:id/container_add_pet");

        //checking for existing temp pet

        if (isElementPresent(By.xpath("//android.widget.TextView[@text='TempPet']"))) {

            logger.error(device + ": Temp Pet already present!");
            driver.findElement(By.xpath("//android.widget.TextView[@text='TempPet']")).click();
            swipeUpToElementId("ru.averia.tracker:id/tv_remove_pet");
            driver.findElementById("ru.averia.tracker:id/tv_remove_pet").click();
            driver.findElementById("ru.averia.tracker:id/md_buttonDefaultPositive").click();
            sleep (5);
        }



        //adding new one
        driver.findElementById("ru.averia.tracker:id/container_add_pet").click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());

        driver.findElement(By.id("ru.averia.tracker:id/et_name")).sendKeys(temp_petname);
        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Порода", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        driver.findElement(By.xpath("//*[@text='Австралийская келпи']")).click();
        driver.findElementById("ru.averia.tracker:id/bt_next").click();

        /*logger.info (" => Strings:");
        logger.info ("Age: " + petage);
        logger.info ("Weight: " + petweight);
        logger.info ("Height: " + petheight);*/

        driver.findElementById("ru.averia.tracker:id/til_age").sendKeys(petage);
        driver.navigate().back();
        driver.findElementById("ru.averia.tracker:id/bt_next").click();

        driver.findElementById("ru.averia.tracker:id/til_weight").sendKeys(petweight);
        driver.findElementById("ru.averia.tracker:id/til_height").sendKeys(petheight);
        driver.navigate().back();
        driver.findElementById("ru.averia.tracker:id/bt_next").click();

        if (isElementPresent(By.xpath("//android.widget.TextView[@text='TempPet']"))) { logger.error(device + ": Temp Pet Added!"); }

    }

    public void deletePetProfileScreen (String device){

        logger.info(device + ": Deleting Temp Pet");

        //checking for existing temp pet and deleting
        swipeUp();

        driver.findElement(By.xpath("//android.widget.TextView[@text='TempPet']")).click();
        swipeUpToElementId("ru.averia.tracker:id/tv_remove_pet");
        driver.findElementById("ru.averia.tracker:id/tv_remove_pet").click();
        driver.findElementById("ru.averia.tracker:id/md_buttonDefaultPositive").click();
        sleep (5);
        if (isElementPresent(By.xpath("//android.widget.TextView[@text='TempPet']"))) { logger.error(device + ": Temp Pet Still Present!"); }

    }

}