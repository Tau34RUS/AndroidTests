package Autotests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Methods {

    private static WebDriver driver;

    void SetUp() throws Exception {
        /* appium setup */
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "Asus");
        capabilities.setCapability("browserName", "Android");
        capabilities.setCapability("platformVersion", "5.0.2");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appPackage", "ru.averia.collars.stg");
        capabilities.setCapability("appActivity", "ru.averia.collars.ui.activities.SplashActivity");

        /* selenium driver setup */
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    void SplashScreen() {

        Assert.assertEquals("Больше никаких потерянных животных", driver.findElement(By.id("ru.averia.collars.stg:id/about_title_dog_1")).getText());
        driver.findElement(By.id("ru.averia.collars.stg:id/bt_next")).click();
        Assert.assertEquals("Мониторинг активности вашего питомца", driver.findElement(By.id("ru.averia.collars.stg:id/about_title_dog_2")).getText());
        driver.findElement(By.id("ru.averia.collars.stg:id/bt_next")).click();
        Assert.assertEquals("Социальная сеть для владельцев собак", driver.findElement(By.id("ru.averia.collars.stg:id/about_title_dog_3")).getText());
        //driver.findElement(By.id("ru.averia.collars.stg:id/bt_register")).click();
    }

    void Register() {

        Random login = new Random();

        String alphabet = "1234567890";
        Variables.userlogin = "";
        for (int i = 0; i < 16; i++) Variables.userlogin += login.nextInt(alphabet.length());
        Variables.userlogin = Variables.userlogin + "@test.user";

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.collars.stg:id/bt_register")).getText());

        driver.findElement(By.id("ru.averia.collars.stg:id/bt_register")).click();

        Assert.assertEquals("Регистрация", driver.findElement(By.id("ru.averia.collars.stg:id/tv_title")).getText());
        //driver.findElement(By.id("ru.averia.collars.stg:id/bt_register")).click();

        WebElement username = driver.findElement(By.id("ru.averia.collars.stg:id/et_email"));
        username.click();
        username.sendKeys(Variables.userlogin);

        WebElement password = driver.findElement(By.id("ru.averia.collars.stg:id/et_password"));
        password.click();
        password.sendKeys(Variables.userpass);

        driver.findElement(By.id("ru.averia.collars.stg:id/bt_register")).click();

        Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.collars.stg:id/bt_add_pet")).getText());

    }

    void Login() {


        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.collars.stg:id/bt_login")).getText());
        driver.findElement(By.id("ru.averia.collars.stg:id/bt_login")).click();

        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.collars.stg:id/tv_title")).getText());
        Assert.assertEquals("Войти", driver.findElement(By.id("ru.averia.collars.stg:id/bt_login")).getText());

        WebElement username = driver.findElement(By.id("ru.averia.collars.stg:id/et_email"));
        username.click();
        username.sendKeys(Variables.userlogin);

        WebElement password = driver.findElement(By.id("ru.averia.collars.stg:id/et_password"));
        password.click();
        password.sendKeys(Variables.userpass);

        driver.findElement(By.id("ru.averia.collars.stg:id/bt_login")).click();

        Assert.assertEquals("Добавить", driver.findElement(By.id("ru.averia.collars.stg:id/bt_add_pet")).getText());

    }

    void Quit() {
        driver.quit();
        }

    void Restart() throws Exception{
        Quit();
        SetUp();
    }

    void AddPet() {
        driver.findElement(By.id("ru.averia.collars.stg:id/maim_menu_action_pet")).click();
/*        ru.averia.collars.stg:id/bt_add_pet
        List<WebElement> add_pet_button = (List<WebElement>) driver.findElement(By.id("ru.averia.collars.stg:id/tv_add_collar"));
        if (add_pet_button.size()>0) {
            //action 1
        }
        else {}
*/
        Assert.assertEquals("Добавить питомца", driver.findElement(By.id("ru.averia.collars.stg:id/tv_description_large")).getText());
        driver.findElement(By.id("ru.averia.collars.stg:id/bt_login")).click();

    }

}
