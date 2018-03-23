package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.support.*;

import java.util.concurrent.*;

public class Main_screen extends Common {

    protected Logger logger;

    public Main_screen(AppiumDriver<MobileElement> driver)  {

        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, com.vars.consts.Timeout, TimeUnit.SECONDS), this);
    }

}
