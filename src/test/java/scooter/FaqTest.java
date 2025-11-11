package scooter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageobjects.ManePage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;

    private int num;
    private String answer;

    public FaqTest(int num, String answer) {
        this.num = num;
        this.answer = answer;
    }


    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][] {
                {0,"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1,"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2,"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
        };

    }
    @Before
    public void startDriver() throws InterruptedException {
        //testFireFoxBrowser();
        testChromeBrowser();
        ManePage manePage = new ManePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        manePage.closeCookie();
    }

    @After
    public void turnOff(){
        driver.quit();
    }


    @Test
    public void textTest() throws InterruptedException {
        ManePage manePage = new ManePage(driver);
        manePage.clickQuestion(num);
        String actualText = manePage.getText(num);
        Assert.assertEquals("Не совпадает текст ответа",answer, actualText);
    }

    private void testChromeBrowser(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized", "--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(chromeOptions);
    }

    private void testFireFoxBrowser(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--kiosk","--no-sandbox","--disable-dev-shm-usage");
        driver = new FirefoxDriver(firefoxOptions);
    }
}
