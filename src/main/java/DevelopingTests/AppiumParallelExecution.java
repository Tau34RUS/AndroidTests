package DevelopingTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumParallelExecution implements Runnable {

    String port;
    String udid;

    public AppiumParallelExecution(String portNumber, String udid) {
        this.port = portNumber;
        this.udid = udid;
    }


    AppiumDriver<WebElement> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();


    private void Setup() {
        capabilities.setCapability("deviceName", "My Mobile Device");
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
        capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

        try {
            driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:" + port + "/wd/hub"), capabilities);
            Thread.sleep(10000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        Runnable r1 = new AppiumParallelExecution("4723", "G1NPFP1202437HN");
        Runnable r2 = new AppiumParallelExecution("4724", "BDE3N1678E001068");
        Runnable r3 = new AppiumParallelExecution("4725", "420268870104");
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
    }

    @Override
    public void run() {
        Setup();
    }
}