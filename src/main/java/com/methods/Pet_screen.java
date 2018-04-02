package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.testng.*;

import java.util.concurrent.*;

import static com.vars.consts.*;
import static com.vars.vars.*;

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

    public void addPet (String device){

        logger.info(device + ": Adding Pet");

        driver.findElement(By.id("ru.averia.tracker:id/maim_menu_action_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_description_large")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/et_name")).click();
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

        driver.findElement(By.id("ru.averia.tracker:id/til_weight")).click();
        driver.findElement(By.id("ru.averia.tracker:id/til_weight")).sendKeys(petweight);

        driver.findElement(By.id("ru.averia.tracker:id/et_height")).click();
        driver.findElement(By.id("ru.averia.tracker:id/et_height")).sendKeys(petheight);

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Добавьте ошейник", driver.findElement(By.id("ru.averia.tracker:id/tv_add_collar_title1")).getText());
        Assert.assertEquals(petname, driver.findElement(By.id("ru.averia.tracker:id/tv_pet_name")).getText());
    }

}