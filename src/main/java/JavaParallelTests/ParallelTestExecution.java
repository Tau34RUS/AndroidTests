package JavaParallelTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class ParallelTestExecution implements Runnable {
    
    String port;
    String udid;

    @Parameters({"server_port", "device"})
    public ParallelTestExecution(String portNumber, String udid, String userlogin, String userpass) {
        this.port = portNumber;
        this.udid = udid;
    }
    AppiumDriver<WebElement> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    void ParallelSetup() {
        capabilities.setCapability("deviceName", "My Mobile Device");
        capabilities.setCapability("udid", udid);
        //capabilities.setCapability("app", "D:\\APK\\ru.averia.tracker.apk");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "ru.averia.tracker");
        capabilities.setCapability("appActivity", "ru.averia.tracker.ui.activities.SplashActivity");

        try {
            //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

    void Register() throws IOException {

        Random login = new Random();

        String alphabet = "1234567890";
        Variables.userlogin = "";
        for (int i = 0; i < 16; i++) Variables.userlogin += login.nextInt(alphabet.length());
        Variables.userlogin = Variables.userlogin + "@test.user";

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/bt_register")).getText());

        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.tracker:id/tv_title")).getText());

        MobileElement username = (MobileElement) driver.findElement(By.id("ru.averia.tracker:id/et_email"));
        username.sendKeys(Variables.userlogin);

        MobileElement password = (MobileElement) driver.findElement(By.id("ru.averia.tracker:id/et_password"));
        password.sendKeys(Variables.userpass);

        HideKeyboard();

        driver.findElement(By.id("ru.averia.tracker:id/bt_register")).click();

        //Allow android actions
        Sleep(15);

        AndroidAllowAccess();
        Sleep(5);
        Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.tracker:id/bt_add_pet")).getText());

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

        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
        Sleep(2);
        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
        Sleep(2);
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_pet").click();
        Sleep(2);
    }

    void Sleep (Integer seconds) {

        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        Runnable r1 = new ParallelTestExecution("4731", "32011059ac215467","1","123456");
        Runnable r2 = new ParallelTestExecution("4732", "G1NPFP1202437HN","1","123456");
        Runnable r3 = new ParallelTestExecution("4733", "621HEBRB2A2W3","1","123456");
        Runnable r4 = new ParallelTestExecution("4734", "5PH6NRWGTOGQWCPR","1","123456");
        //Runnable r5 = new ParallelTestExecution("4735","", "1","123456");
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
        new Thread(r4).start();
        //new Thread(r5).start();
    }

    @Override
    public void run() {
        ParallelSetup();
        SplashScreen();
        try {
            Register();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (1==1) {
            ScreensShuffle();
            Sleep(5);
        }
    }
}