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

    public void mapActions() {

        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        driver.findElementById("ru.averia.tracker:id/ib_pet_position").click();
        driver.findElementById("ru.averia.tracker:id/ib_self_position").click();
        driver.findElementById("ru.averia.tracker:id/ib_other_pets").click();

    }

}