package AlphaTests;

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
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("WeakerAccess")
public class Methods {

    AppiumDriver<MobileElement> driver;
    protected static Logger logger;
    String folder_name;


    public void SetUp() throws MalformedURLException {

        logger = Logger.getLogger("MethodsTestLogger");
        logger.info("Starting Alpha Test Program");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android");
        logger.info("Is Mac? " + OsUtils.OS.MAC.equals(OsUtils.getOs()));

        // use already installed app
        //if (OsUtils.OS.MAC.equals(OsUtils.getOs())){capabilities.setCapability("app", Constants.appath_mac);}
        //else {capabilities.setCapability("app", Constants.appath_win);}
        //end of use installed app

        capabilities.setCapability("appPackage", Constants.AppPKG);
        capabilities.setCapability("appActivity", Constants.AppAct);
        capabilities.setCapability("UDID", Constants.phone_lg);

        //selenium and appium driver setup
        //noinspection Convert2Diamond
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+Constants.port+"/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Constants.Timeout, TimeUnit.SECONDS);

        Variables.screensize = driver.manage().window().getSize();
        Variables.devicename = driver.getCapabilities().getCapability("deviceName").toString();
        logger.info("Screen size: " + Variables.screensize);
        logger.info("Device name: " + Variables.devicename);

    }


    public void Restart() throws MalformedURLException {
        Quit();
        SetUp();
    }

    public void SplashScreen() {

        Assert.assertEquals("Больше никаких потерянных животных", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_1")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        Assert.assertEquals("Мониторинг активности вашего питомца", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_2")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        Assert.assertEquals("Социальная сеть для владельцев собак", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_3")).getText());

    }

    public void Register() {

        Random login = new Random();

        String alphabet = "1234567890";
        Variables.userlogin = "";
        for (int i = 0; i < 16; i++) Variables.userlogin += login.nextInt(alphabet.length());
        Variables.userlogin = Variables.userlogin + "@test.user";

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/bt_register")).getText());

        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        //Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());

        Sleep(5);

        MobileElement username = driver.findElementById("ru.averia.tracker:id/et_email");

        username.sendKeys(Variables.userlogin);

        MobileElement password = driver.findElementById("ru.averia.tracker:id/et_password");

        password.sendKeys(Variables.userpass);

        logger.info("Userlogin: " + Variables.userlogin);
        logger.info("Userpass:  " + Variables.userpass);

        //HideKeyboard();
        //driver.navigate().back();
        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        //Allow android actions
        AndroidAllowAccess();

        Assert.assertEquals("Добавить", driver.findElementById("ru.averia.tracker:id/bt_add_pet").getText());

        logger.info("Registration done");

    }

    public void Login() {


        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/bt_login")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_login")).click();

        //Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());
        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.tracker:id/bt_login")).getText());
        Sleep(5);
        MobileElement username = driver.findElement(By.id("ru.averia.tracker:id/et_email"));
        username.click();
        username.sendKeys(Variables.userlogin);

        MobileElement password = driver.findElement(By.id("ru.averia.tracker:id/et_password"));
        password.click();
        password.sendKeys(Variables.userpass);
        driver.findElementById("ru.averia.tracker:id/bt_login").click();

        AndroidAllowAccess();

        try {
            Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).getText());
        }catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No Add Pet button, already added?");
        }
    }

    public void Quit() {
        driver.quit();
    }

    public void AddPet (){

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

        PhonePhoto();

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

        driver.findElementById("ru.averia.tracker:id/et_age").sendKeys(Variables.birthyear);

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

    public void AndroidAllowAccess() {
        try {
            driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No Permissions requested");
        }

    }

    public void AddCollar() {

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

    public void ScreensShuffle() {
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_pet").click();
    }

    public void Map() {
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        driver.findElementById("ru.averia.tracker:id/ib_pet_position").click();
        driver.findElementById("ru.averia.tracker:id/ib_self_position").click();
        driver.findElementById("ru.averia.tracker:id/ib_other_pets").click();
        //Sleep(20);
        //driver.findElementByXPath("//android.view.View[@content-desc=\"Карта Google\"]/android.view.View").clear();
    }

    public void UserProfile() {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        driver.findElementById("ru.averia.tracker:id/iv_ava").clear();
        driver.findElementById("ru.averia.tracker:id/tv_name").clear();
        driver.findElementById("ru.averia.tracker:id/tv_info").clear();

        switch (Variables.devicename) {
            case (Constants.phone_nexus_5): //damned cyanogen
                break;
            default:

            driver.findElementById("ru.averia.tracker:id/bt_edit_profile").click();
            driver.findElementById("ru.averia.tracker:id/et_last_name").sendKeys("Tester");
            logger.info("Swipe up");
            SwipeUp();

            logger.info("Fill phone number");
            Random login = new Random();

            String alphabet = "1234567890";
            Variables.phonenumber = "";
            for (int i = 0; i < 11; i++) Variables.phonenumber += login.nextInt(alphabet.length());
            driver.findElementById("ru.averia.tracker:id/et_phone").sendKeys(Variables.phonenumber);

            logger.info("Swipe down");
            SwipeDown();

            driver.findElementById("ru.averia.tracker:id/container_avatar").click();

            PhonePhoto();

            driver.findElement(By.id("ru.averia.tracker:id/crop_image_menu_crop")).click();
            Sleep(5);
            driver.findElementById("ru.averia.tracker:id/iv_save").click();
            Sleep(5);
            logger.info("Saving user profile changes");
            try {driver.findElementById("ru.averia.tracker:id/iv_save").click();}
            catch (org.openqa.selenium.NoSuchElementException e) {logger.info("Already saved?");}
                break;
        }
    }

    public void SwipeUp() {

        int starty = (int) (Variables.screensize.height * 0.50);
        int endy = (int) (Variables.screensize.height * 0.20);
        int startx = Variables.screensize.width / 2;
        driver.swipe(startx,starty,startx,endy,300);
        driver.swipe(startx,starty,startx,endy,300);
        Sleep(1);
    }

    public void SwipeDown() {

        int starty = (int) (Variables.screensize.height * 0.20);
        int endy = (int) (Variables.screensize.height * 0.70);
        int startx = Variables.screensize.width / 2;
        driver.swipe(startx,starty,startx,endy,300);
        driver.swipe(startx,starty,startx,endy,300);
        Sleep(1);
    }

    public void Logout() {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        (new TouchAction(driver)).press(204, 672).waitAction(100).moveTo(22,-437).release().perform();
        driver.findElementById("ru.averia.tracker:id/tv_logout").click();
        driver.findElementById("ru.averia.tracker:id/about_title_dog_1").clear();

    }

    public void DeletePet() {

        driver.findElementById("ru.averia.tracker:id/tv_pet_name").click();
        driver.findElementById("ru.averia.tracker:id/iv_check").click();
        driver.findElementById("ru.averia.tracker:id/md_buttonDefaultPositive").click();

    }

    public void PhonePhoto() {
        AndroidAllowAccess();
        switch (Variables.devicename) {

            case (Constants.phone_sony_xperia):
                //fkn experia does not want to focus an image
                logger.info("Sony Xperia X Perfomance photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]").click();
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout").click();

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
            case (Constants.phone_nexus_5):
                logger.info("Nexus 5 photo sequence applied");
                AndroidAllowAccess();
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout").click();
                driver.findElementByAccessibilityId("Затвор").click();
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

    public void PetEdit() {

        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup[1]").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.Button").click();

        driver.findElementById("ru.averia.tracker:id/et_name").sendKeys("1");

        //HideKeyboard();

        SwipeUp();

        driver.findElementById("ru.averia.tracker:id/et_weight").sendKeys("33");

        driver.findElementById("ru.averia.tracker:id/et_height").sendKeys("33");

        driver.findElementById("ru.averia.tracker:id/iv_save").click();

        //HideKeyboard();

    }

    public void ShowAppStats() throws IOException {

        logger.info(Runtime.getRuntime().exec("adb shell \"dumpsys meminfo 'ru.averia.tracker'| grep TOTAL \"").getOutputStream());

    }
}


