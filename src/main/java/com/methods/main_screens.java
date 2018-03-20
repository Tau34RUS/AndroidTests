package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.support.*;

import java.util.concurrent.*;

public class main_screens extends common {

    protected Logger logger;

    public main_screens(AppiumDriver<MobileElement> driver)  {

        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, com.vars.consts.Timeout, TimeUnit.SECONDS), this);
    }

}
