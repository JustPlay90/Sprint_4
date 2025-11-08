package scooter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageobjects.ManePage;

import java.time.Duration;

import static pageobjects.ManePage.*;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;

    private final By question;
    private final By answer;
    private final String answerText;

    public FaqTest(By question, By answer, String answerText) {
        this.question = question;
        this.answer = answer;
        this.answerText = answerText;
    }


    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][] {
                {question1,answer1,answer1Text},
                {question2,answer2,answer2Text},
                {question3,answer3,answer3Text},
                {question4,answer4,answer4Text},
                {question5,answer5,answer5Text},
                {question6,answer6,answer6Text},
                {question7,answer7,answer7Text},
                {question8,answer8,answer8Text}
        };

    }
    @Before
    public void startDriver() throws InterruptedException {
       /* ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized", "--no-sandbox", "--headless", "--disable-dev-shm-usage", "--start-maximized");
        driver = new ChromeDriver(chromeOptions);*/
         FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--kiosk", "--no-sandbox", "--headless", "--disable-dev-shm-usage", "--start-maximized");
        driver = new FirefoxDriver(firefoxOptions);
        ManePage manePage = new ManePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
        manePage.clickQuestion(question);
        String actualText = manePage.getTest(answer);
        Assert.assertEquals("Не совпадает текст ответа",answerText, actualText);
    }
}
