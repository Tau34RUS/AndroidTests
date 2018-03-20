package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.testng.*;

import java.util.*;
import java.util.concurrent.*;

import static com.vars.consts.*;
import static com.vars.vars.*;

public class start_screens extends common{

    protected Logger logger;

    public start_screens(AppiumDriver<MobileElement> driver)  {
        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout, TimeUnit.SECONDS), this);
    }

    public void SplashScreen() {
        Sleep(5);
        Assert.assertEquals("Больше никаких потерянных животных", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_1")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        Assert.assertEquals("Мониторинг активности вашего питомца", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_2")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        Assert.assertEquals("Социальная сеть для владельцев собак", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_3")).getText());

    }

    public void Register(String device) {

        logger.info(device + ": Starting Registration");

        Random login = new Random();

        String alphabet = "1234567890";
        userlogin = "";
        for (int i = 0; i < 16; i++) userlogin += login.nextInt(alphabet.length());
        userlogin = userlogin + "@test.user";

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/bt_register")).getText());

        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        //Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());

        Sleep(5);

        driver.findElementById("ru.averia.tracker:id/et_email").sendKeys(userlogin);

        driver.findElementById("ru.averia.tracker:id/et_password").sendKeys(userpass);

        logger.info(device + ": Userlogin: " + userlogin);
        logger.info(device + ": Userpass:  " + userpass);

        //HideKeyboard();
        //driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        //Allow android actions
        AndroidAllowAccess();

        Assert.assertEquals("Добавить", driver.findElementById("ru.averia.tracker:id/bt_add_pet").getText());

        logger.info(device + ": Registration done");

    }

    public void Login(String device) {

        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/bt_login")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_login")).click();

        //Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/bt_login")).getText());
        Sleep(5);
        driver.findElement(By.id("ru.averia.tracker:id/et_email")).sendKeys(userlogin);

        driver.findElement(By.id("ru.averia.tracker:id/et_password")).sendKeys(userpass);
        driver.findElementById("ru.averia.tracker:id/bt_login").click();

        AndroidAllowAccess();

        try {
            Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).getText());
        }catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info(device + ": No Add Pet button, already added?");
        }
    }



}
