package AppiumBased;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("WeakerAccess")
public class Methods {



    AppiumDriver<WebElement> driver;
    protected static Logger logger;
    String folder_name;

    void SetUp() throws Exception {

        logger = Logger.getLogger("MethodsTestLogger");

        /* appium setup */
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "Android");
        /*capabilities.setCapability("browserName", "Android");
        capabilities.setCapability("platformVersion", "5.0.2");
        capabilities.setCapability("udid", "G1NPFP1202437HN");*/
        capabilities.setCapability("platformName", "Android");

        if (OsUtils.OS.MAC.equals(OsUtils.getOs())){capabilities.setCapability("app", Constants.appath_mac);}
        else {capabilities.setCapability("app", Constants.appath_win);}

        logger.info("Is Mac? " + OsUtils.OS.MAC.equals(OsUtils.getOs()));

        capabilities.setCapability("appPackage", Constants.AppPKG);
        capabilities.setCapability("appActivity", Constants.AppAct);

        /* selenium and appium driver setup */

        //noinspection Convert2Diamond
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4731/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Constants.Timeout, TimeUnit.SECONDS);
        Variables.screensize = driver.manage().window().getSize();
        Variables.devicename = driver.getCapabilities().getCapability("deviceName").toString();
        logger.info("Screen size: " + Variables.screensize);
        logger.info("Device name: " + Variables.devicename);

    }

    void SplashScreen() {

        Assert.assertEquals("Больше никаких потерянных животных", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_1")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        Assert.assertEquals("Мониторинг активности вашего питомца", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_2")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        Assert.assertEquals("Социальная сеть для владельцев собак", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_3")).getText());
        //driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();
    }

    void Register() {

        Random login = new Random();

        String alphabet = "1234567890";
        Variables.userlogin = "";
        for (int i = 0; i < 16; i++) Variables.userlogin += login.nextInt(alphabet.length());
        Variables.userlogin = Variables.userlogin + "@test.user";

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/bt_register")).getText());

        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());

        WebElement username = driver.findElement(By.id("ru.averia.tracker:id/et_email"));
        username.click();
        username.sendKeys(Variables.userlogin);

        WebElement password = driver.findElement(By.id("ru.averia.tracker:id/et_password"));
        password.click();
        password.sendKeys(Variables.userpass);

        logger.info("Userlogin: " + Variables.userlogin);
        logger.info("Userpass:  " + Variables.userpass);

        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).getText());

    }

    void Login() {


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

        driver.findElement(By.id("ru.averia.tracker:id/bt_login")).click();

        Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).getText());

    }

    void Quit() {
        driver.quit();
    }

    void Restart() throws Exception{
        Quit();
        SetUp();
    }


    void AddPet() {
        driver.findElement(By.id("ru.averia.tracker:id/maim_menu_action_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_description_large")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        WebElement petname = driver.findElement(By.id("ru.averia.tracker:id/et_name"));
        petname.click();
        petname.sendKeys(Variables.petname);
        //TODO add gender random selection
        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        driver.findElement(By.id("ru.averia.tracker:id/iv_pet_ava")).click();

        Assert.assertEquals("Выбрать источник", driver.findElement(By.id("android:id/title")).getText());

        List<WebElement> choiseslist = driver.findElements(By.className("android.widget.LinearLayout"));

        logger.info("List: " + choiseslist);

        choiseslist.get(1).click();


        //TODO check cameras on different brands - try -> catch

        //Asus
        driver.findElement(By.id("com.asus.camera:id/button_capture")).click();

        driver.findElement(By.id("com.asus.camera:id/button_used")).click();

        //Xiomi

        //HTC

        //LG
        
        


        driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

 /*     TODO dig out text input issue, Antons shitty code?
        WebElement breed = driver.findElement(By.id("ru.averia.tracker:id/et_breed_title"));
        breed.click();
        breed.sendKeys(Variables.breed);

        driver.findElements(By.className("android.widget.EditText")).get(0).sendKeys(Variables.breed);

        ru.averia.tracker:id/et_breed_title
 */
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

    //TODO Port to appium, selenium does not support good swipe
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

}
