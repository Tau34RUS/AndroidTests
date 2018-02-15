package NGParallelTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ParallelTestExecution {
    
    String port;
    String device;

    @Parameters({"server_port", "device"})
    public ParallelTestExecution(String port, String device) {
        this.port = port;
        this.device = device;
    }

    AppiumDriver<MobileElement> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    void ParallelSetup() {
        capabilities.setCapability("deviceName", device);
        //capabilities.setCapability("app", "D:\\APK\\ru.averia.tracker.apk");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "ru.averia.tracker");
        capabilities.setCapability("appActivity", "ru.averia.tracker.ui.activities.SplashActivity");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
            //Thread.sleep(5000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    void SplashScreen() {

        //Assert.assertEquals("Больше никаких потерянных животных", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_1")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        //Assert.assertEquals("Мониторинг активности вашего питомца", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_2")).getText());
        driver.findElement(By.id("ru.averia.tracker:id/bt_next")).click();
        //Assert.assertEquals("Социальная сеть для владельцев собак", driver.findElement(By.id("ru.averia.tracker:id/about_title_dog_3")).getText());

    }

    public void Quit() {
        driver.quit();
    }

    void Register() {

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

        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        //Allow android actions
        AndroidAllowAccess();

    }

    void HideKeyboard() throws IOException {

        Process p = null;
        try {
            p = Runtime.getRuntime().exec("adb shell dumpsys input_method | grep mInputShown");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String outputText = "";

        while ((outputText = in.readLine()) != null) {

            if(!outputText.trim().equals("")){
                String keyboardProperties[]=outputText.split(" ");
                String keyValue[]=keyboardProperties[keyboardProperties.length-1].split("=");

                String softkeyboardpresenseValue=keyValue[keyValue.length-1];
                if(softkeyboardpresenseValue.equalsIgnoreCase("false")){
                }else{
                    driver.hideKeyboard();
                }
            }
        }
        in.close();

    }

    void AndroidAllowAccess() {
        try {
            driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {}

    }

    void ScreensShuffle() {
        Sleep(5);
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        Sleep(5);
        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        Sleep(5);
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_pet").click();
    }

    void Sleep (Integer seconds) {

        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @BeforeTest(alwaysRun = true)
    void BeforeSuite() {
        ParallelSetup();}
    @AfterTest
    void AfterSuite() {
        Quit();
    }

    @Test
    void TestRegister() throws IOException {
        SplashScreen();
        Register();
    }

    @Test (dependsOnMethods = "TestRegister")
    void TestScreenShuffle() {
        while (1==1) {
            ScreensShuffle();
        }
    }
}