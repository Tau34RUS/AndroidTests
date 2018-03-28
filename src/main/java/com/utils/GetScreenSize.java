package com.utils;

import com.methods.*;
import com.vars.*;
import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.log4j.*;
import org.openqa.selenium.support.*;

import java.util.concurrent.*;

public class GetScreenSize extends Common {

    protected Logger logger;

    public GetScreenSize(AppiumDriver<MobileElement> driver)
    {
        super(driver);
        logger = Logger.getLogger("Screenshot");
        PageFactory.initElements(new AppiumFieldDecorator(driver, com.vars.consts.Timeout, TimeUnit.SECONDS), this);
    }

    public void getScreenSize() {

        vars.screensize = driver.manage().window().getSize();

    }


}
