package AppiumBased;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("WeakerAccess")
public class Methods_Home {



    AppiumDriver<MobileElement> driver;
    protected static Logger logger;
    String folder_name;

    void SetUp() throws MalformedURLException {

        logger = Logger.getLogger("MethodsTestLogger");

        /* appium setup */
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");

        if (OsUtils.OS.MAC.equals(OsUtils.getOs())){capabilities.setCapability("app", Constants.appath_mac);}
        else {capabilities.setCapability("app", Constants.appath_win);}

        logger.info("Is Mac? " + OsUtils.OS.MAC.equals(OsUtils.getOs()));

        capabilities.setCapability("appPackage", Constants.AppPKG);
        capabilities.setCapability("appActivity", Constants.AppAct);

        /* selenium and appium driver setup */

        //noinspection Convert2Diamond
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4731/wd/hub"), capabilities);
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

        MobileElement username = driver.findElement(By.id("ru.averia.tracker:id/et_email"));
        username.click();
        username.sendKeys(Variables.userlogin);

        MobileElement password = driver.findElement(By.id("ru.averia.tracker:id/et_password"));
        password.click();
        password.sendKeys(Variables.userpass);

        logger.info("Userlogin: " + Variables.userlogin);
        logger.info("Userpass:  " + Variables.userpass);
        driver.hideKeyboard();
        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        //Allow android actions
        AndroidAllowAccess();
        Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).getText());

        logger.info("Registration done");

    }

    void Login() {


        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/bt_login")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_login")).click();

        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/bt_login")).getText());

        MobileElement username = driver.findElement(By.id("ru.averia.tracker:id/et_email"));
        username.click();
        username.sendKeys(Variables.userlogin);

        MobileElement password = driver.findElement(By.id("ru.averia.tracker:id/et_password"));
        password.click();
        password.sendKeys(Variables.userpass);
        driver.hideKeyboard();
        driver.findElementById("ru.averia.tracker:id/bt_login").click();

        AndroidAllowAccess();

        try {
            Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).getText());
        }catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No Add Pet button,is a pet already added?");
        }
    }

    void Quit() {
        driver.quit();
    }

    void Restart() throws MalformedURLException {
        Quit();
        SetUp();
    }


    void AddPet (){

        driver.findElement(By.id("ru.averia.tracker:id/maim_menu_action_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_description_large")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).click();
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        MobileElement petname = driver.findElement(By.id("ru.averia.tracker:id/et_name"));
        petname.click();
        petname.sendKeys(Variables.petname);
        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        driver.findElement(By.id("ru.averia.tracker:id/iv_pet_ava")).click();

        AndroidAllowAccess();

        PhonePhoto();

        driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        //Shitty Magic
        (new TouchAction(driver)).tap(400, 400).perform();
        try {
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[4]").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.warn("No List Element 'Breed' Found!");
        }

        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Возраст и дата рождения", driver.findElement(By.id("ru.averia.tracker:id/tv_cap")).getText());
        MobileElement birthyear = driver.findElement(By.id("ru.averia.tracker:id/et_year"));
        birthyear.sendKeys(Variables.birthyear);

        MobileElement birthmonth = driver.findElement(By.id("ru.averia.tracker:id/et_month"));
        birthmonth.sendKeys(Variables.birthmonth);

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        MobileElement petweight = driver.findElement(By.id("ru.averia.tracker:id/til_weight"));
        petweight.click();
        petweight.sendKeys(Variables.petweight);

        MobileElement petwheight = driver.findElement(By.id("ru.averia.tracker:id/et_height"));
        petwheight.click();
        petwheight.sendKeys(Variables.petheight);

        driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();

        Assert.assertEquals("Добавьте ошейник", driver.findElement(By.id("ru.averia.tracker:id/tv_add_collar_title1")).getText());
        Assert.assertEquals(Variables.petname, driver.findElement(By.id("ru.averia.tracker:id/tv_pet_name")).getText());
    }

    public void captureScreenShots() throws IOException {
        folder_name="screenshot";
        File f= driver.getScreenshotAs(OutputType.FILE);
        //create dir with given folder name
        //noinspection ResultOfMethodCallIgnored
        new File(folder_name).mkdir();
        //copy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + "LastFailScreenshot.png"));
    }

    public void Sleep (Integer seconds) {

        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void AndroidAllowAccess() {
        try {
            driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No Permissions requested");
        }

    }

    void AddCollar() {

        Assert.assertEquals("Добавьте ошейник" ,driver.findElementById("ru.averia.tracker:id/tv_add_collar_title1").getText());
        driver.findElementById("ru.averia.tracker:id/tv_add_collar").click();

        driver.findElementById("ru.averia.tracker:id/bt_search").click();

        if(driver.findElements(By.id("ru.averia.tracker:id/pb_loading")).isEmpty()) {
            throw new EmptyStackException();
        }

        driver.findElementById("ru.averia.tracker:id/iv_back").click();

    }

    void ScreensShuffle() {

        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        Sleep(2);
        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        Sleep(2);
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_pet").click();
        Sleep(2);
    }

    void Map() {
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        driver.findElementById("ru.averia.tracker:id/ib_pet_position").click();
        driver.findElementById("ru.averia.tracker:id/ib_self_position").click();
        driver.findElementById("ru.averia.tracker:id/ib_other_pets").click();
        Sleep(30);
        driver.findElementByXPath("//android.view.View[@content-desc=\"Карта Google\"]/android.view.View").clear();
    }

    void UserProfile() {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        driver.findElementById("ru.averia.tracker:id/iv_ava").clear();
        driver.findElementById("ru.averia.tracker:id/tv_name").clear();
        driver.findElementById("ru.averia.tracker:id/tv_info").clear();
        driver.findElementById("ru.averia.tracker:id/bt_edit_profile").click();
        driver.findElementById("ru.averia.tracker:id/et_last_name").sendKeys("Tester");
        logger.info("Swipe up");
        SwipeUp();

        logger.info("Fill phone number");
        driver.findElementById("ru.averia.tracker:id/et_phone").sendKeys("79999999999");

        logger.info("Swipe down");
        SwipeDown();

        //driver.findElementById("ru.averia.tracker:id/container_avatar").click();

        //PhonePhoto();

        driver.findElementById("ru.averia.tracker:id/iv_save").click();

    }

    void SwipeUp() {

        (new TouchAction(driver)).press(236,466).moveTo(4,-252).release().perform();

    }

    void SwipeDown() {

        (new TouchAction(driver)).press(215,148).moveTo(-1,335).release().perform();

    }

    void Logout() {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        (new TouchAction(driver)).press(204, 672).waitAction(100).moveTo(22,-437).release().perform();
        driver.findElementById("ru.averia.tracker:id/tv_logout").click();
        driver.findElementById("ru.averia.tracker:id/about_title_dog_1").clear();

    }

    void DeletePet() {

        driver.findElementById("ru.averia.tracker:id/tv_pet_name").click();
        driver.findElementById("ru.averia.tracker:id/iv_check").click();
        driver.findElementById("ru.averia.tracker:id/md_buttonDefaultPositive").click();

    }

    void PhonePhoto() {

        switch (Variables.devicename) {

            case (Constants.phone_wileyfox):
                logger.info("Wileyfox photo sequence applied");
                List<MobileElement> choiseslist_wf = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_wf);
                choiseslist_wf.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();
                break;
            case (Constants.phone_asuszenpad):
                logger.info("Asus Zenpad photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.ListView/android.widget.LinearLayout[1]").click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();
                break;
            case (Constants.phone_htc):
                List<MobileElement> choiseslist_htc = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_htc);
                choiseslist_htc.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();
                break;
            case (Constants.phone_samsung_j1):
                logger.info("Samsung J1 photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/com.android.internal.widget.ResolverDrawerLayout/android.widget.GridView/android.widget.LinearLayout[1]/android.widget.ImageView").click();
                driver.findElementByAccessibilityId("MENUID_SHUTTER").click();
                driver.findElementById("com.sec.android.app.camera:id/okay").click();

                break;
            case (Constants.phone_samsung_edge):
                logger.info("Samsung Edge photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/com.android.internal.widget.ViewPager/android.widget.LinearLayout/android.widget.GridView/android.widget.LinearLayout[1]").click();
                Sleep(5);
                driver.findElementByXPath("(//GLButton[@content-desc=\"NONE\"])[3]").click();
                driver.findElementById("com.sec.android.app.camera:id/okay").click();

                break;
            case (Constants.phone_lg):
                logger.info("LG photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout/android.widget.LinearLayout[2]").click();
                Sleep(2);
                (new TouchAction(driver)).tap(109, 203).perform();
                Sleep(2);
                (new TouchAction(driver)).tap(109, 203).perform();
                Sleep(1);
                break;
            case (Constants.phone_meizu_n5):
                List<MobileElement> choiseslist_meizu_n5 = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_meizu_n5);
                choiseslist_meizu_n5.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();
                break;
            case (Constants.phone_xiomi_x4_note):
                logger.info("Xiomi X4 Note photo sequence applied");
                List<MobileElement> choiseslist_xiomi_x4_note = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_xiomi_x4_note);
                choiseslist_xiomi_x4_note.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();
                break;
            case (Constants.phone_honor_c3):
                logger.info("Honor 3C photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.view.ViewPager/android.widget.GridView/android.widget.LinearLayout[1]").click();
                driver.findElementByAccessibilityId("Сфотографировать").click();
                driver.findElementByAccessibilityId("Готово").click();
                break;
            default:
                logger.info("Default photo action applied");
                Sleep(2);
                AndroidAllowAccess();
                List<MobileElement> choiseslist = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist);
                choiseslist.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();
                break;
        }

    }

}
