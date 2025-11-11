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
import pageobjects.OrderPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String destinationAddress;
    private final String metroStation;
    private final String telefonNumber;

    private final String dateToDelliver;
    private final String rentalPeriod;
    private final String scooterCollor;
    private final String commentForCourier;

    private  final String button;


    public OrderTest(String button, String name, String surname, String destinationAddress, String metroStation,
                     String telefonNumber, String dateToDelliver, String rentalPeriod, String scooterCollor,
                     String commentForCourier) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.destinationAddress = destinationAddress;
        this.metroStation = metroStation;
        this.telefonNumber = telefonNumber;
        this.dateToDelliver = dateToDelliver;
        this.rentalPeriod = rentalPeriod;
        this.scooterCollor = scooterCollor;
        this.commentForCourier = commentForCourier;
    }


    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][] {
                {"верх", "Игорь", "Игоревский", "Кирова 66", "Черкизовская", "79139133784", "10.12.2025", "двое суток", "черный", "Сбазибо"},
                {"низ","Александр", "Крузенштерн", "Москва", "Черкизовская", "79139133784", "14.9.2025", "сутки", "серый", "" }
        };
    }
    @Before
    public void startDriver() throws InterruptedException {
            //testFireFoxBrowser();
            testChromeBrowser();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            driver.get("https://qa-scooter.praktikum-services.ru/");
            ManePage manePage = new ManePage(driver);
            manePage.closeCookie();

    }

    @After
    public void turnOff(){
        driver.quit();
    }


    @Test
    public void orderTest() throws InterruptedException {
        OrderPage orderPage = new OrderPage(driver);
        ManePage manePage = new ManePage(driver);
        //Клик по кнопке "Заказать"
        manePage.clickOrderButton(button);
        //заполнить данные пользователя на первой странице и нажать кнопку "Далее"
        orderPage.setUserParams(name, surname, destinationAddress, metroStation,telefonNumber);
        //заполнить второй страници Заказа ( Дата доставки, срок аренды, выбор цвета, комментарий) и Кликнуть кнопку "Заказать"
        orderPage.setOrderParams(dateToDelliver, rentalPeriod, scooterCollor,commentForCourier);
        //Кликнуть "Да" в меню подтверждения заказа
        orderPage.clickConfirmationButton();
        //проверить что отображается текст :"Заказ оформлен"
        Assert.assertTrue("Окно не активно",orderPage.checkConfirmationIsActive());
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
