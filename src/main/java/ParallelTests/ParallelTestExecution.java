package ParallelTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import org.apache.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;


public class ParallelTestExecution implements Runnable {


    String port;
    String udid;
    String folder_name;

    Logger logger;

    public ParallelTestExecution(String portNumber, String udid, String userlogin, String userpass) {
        this.port = portNumber;
        this.udid = udid;
    }
    AppiumDriver<WebElement> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    private void ParallelSetup() {
        capabilities.setCapability("deviceName", "My Mobile Device");
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("app", "D:\\APK\\ru.averia.tracker.apk");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "ru.averia.tracker");
        capabilities.setCapability("appActivity", "ru.averia.tracker.ui.activities.SplashActivity");

        try {
            driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:" + port + "/wd/hub"), capabilities);
            Thread.sleep(10000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void SplashScreen() {
        Assert.assertEquals("Больше никаких потерянных животных", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_1")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        Assert.assertEquals("Мониторинг активности вашего питомца", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_2")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        Assert.assertEquals("Социальная сеть для владельцев собак", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_3")).getText());
    }

    void Register() {

        Random login = new Random();

        String alphabet = "1234567890";
        String userlogin = "";
        for (int i = 0; i < 16; i++) userlogin += login.nextInt(alphabet.length());
        userlogin = userlogin + "@test.user";

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/bt_register")).getText());

        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());

        WebElement username = driver.findElement(By.id("ru.averia.tracker:id/et_email"));
        username.click();
        username.sendKeys(userlogin);

        WebElement password = driver.findElement(By.id("ru.averia.tracker:id/et_password"));
        password.click();
        password.sendKeys(Variables.userpass);

        //logger.info("Userlogin: " + userlogin);
        //logger.info("Userpass:  " + Variables.userpass);
        driver.hideKeyboard();
        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void Login() {


        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/bt_login")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_login")).click();

        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/bt_login")).getText());

        WebElement username = driver.findElement(By.id("ru.averia.tracker:id/et_email"));
        username.click();
        username.sendKeys(Variables.userlogin);

        WebElement password = driver.findElement(By.id("ru.averia.tracker:id/et_password"));
        password.click();
        password.sendKeys(Variables.userpass);
        driver.hideKeyboard();
        driver.findElement(By.id("ru.averia.tracker:id/bt_login")).click();

        Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).getText());

    }

    public void Quit() {

        driver.quit();
    }

    public void AddPet() {
        driver.findElement(By.id("ru.averia.tracker:id/maim_menu_action_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_description_large")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        WebElement petname = driver.findElement(By.id("ru.averia.tracker:id/et_name"));
        petname.click();
        petname.sendKeys(Variables.petname);

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        driver.findElement(By.id("ru.averia.tracker:id/iv_pet_ava")).click();

        Assert.assertEquals("Выбрать источник", driver.findElement(By.id("android:id/title")).getText());

        List<WebElement> choiseslist = driver.findElements(By.className("android.widget.LinearLayout"));

        logger.info("List: " + choiseslist);

        choiseslist.get(1).click();


        //TODO check cameras on different brands - try -> catch

        switch(Variables.devicename) {
            case "G1NPFP1202437HN": //Asus Pad
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();
                break;
            case "5PH6NRWGTOGQWCPR": //Honor 3C
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.view.ViewPager/android.widget.GridView/android.widget.LinearLayout[1]/android.widget.ImageView").click();
                driver.findElementById("com.huawei.camera:id/control_component_layout").click();
                break;
        }

        driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        //Appium Magic

        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.View[1]")).click();
//        MobileElement el1 = (MobileElement) adriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.View[1]");
//        el1.click();
//        adriver.navigate().back();

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Возраст и дата рождения", driver.findElement(By.id("ru.averia.tracker:id/tv_cap")).getText());
        WebElement birthyear = driver.findElement(By.id("ru.averia.tracker:id/et_year"));
        birthyear.click();
        birthyear.sendKeys(Variables.birthyear);

        WebElement birthmonth = driver.findElement(By.id("ru.averia.tracker:id/et_month"));
        birthmonth.click();
        birthmonth.sendKeys(Variables.birthmonth);

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        WebElement petweight = driver.findElement(By.id("ru.averia.tracker:id/til_weight"));
        petweight.click();
        petweight.sendKeys(Variables.petweight);

        WebElement petwheight = driver.findElement(By.id("ru.averia.tracker:id/et_height"));
        petwheight.click();
        petwheight.sendKeys(Variables.petheight);

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Добавьте ошейник", driver.findElement(By.id("ru.averia.tracker:id/tv_add_collar_title1")).getText());
        Assert.assertEquals(Variables.petname, driver.findElement(By.id("ru.averia.tracker:id/tv_pet_name")).getText());
    }

    public void HorizontalScrollL2R () {
        Dimension ScreenSize = driver.manage().window().getSize();
        //Find swipe start and end point from screen's with and height
        int startx = (int) (ScreenSize.width * 0.70);
        int endx = (int) (ScreenSize.width * 0.30);
        int starty = ScreenSize.height / 2;
        //new TouchActions(driver).down(startx, starty).move(endx, starty).perform();
    }

    public void captureScreenShots() throws IOException {
        folder_name="screenshot";
        File f= driver.getScreenshotAs(OutputType.FILE);
        //create dir with given folder name
        new File(folder_name).mkdir();
        //coppy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + "LastFailScreenshot.png"));
    }

    public static void main(String args[]) {
        Runnable r1 = new ParallelTestExecution("4731", "5PH6NRWGTOGQWCPR","1","123456");
        Runnable r2 = new ParallelTestExecution("4732", "LGK430V479D6E6","1","123456");
        Runnable r3 = new ParallelTestExecution("4733", "abcce1d","1","123456");
        Runnable r4 = new ParallelTestExecution("4734", "32011059ac215467","1","123456");
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
        new Thread(r4).start();
    }

    @Override
    public void run() {
        while (1==1) {
            ParallelSetup();
            SplashScreen();
            Register();
            Quit();
        }
    }
}