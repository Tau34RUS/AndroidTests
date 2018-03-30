package com.methods;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.support.*;

import java.util.concurrent.*;

import static com.vars.consts.*;

public class Social_screen extends Common{

    protected Logger logger;

    public Social_screen(AppiumDriver<MobileElement> driver)  {
        super(driver);
        logger = Logger.getLogger("AndroidTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout, TimeUnit.SECONDS), this);
    }



}
