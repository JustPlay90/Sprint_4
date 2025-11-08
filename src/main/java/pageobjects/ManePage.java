package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManePage {

    private WebDriver driver;

    public ManePage(WebDriver driver){
        this.driver = driver;
    }

    // Локаторы для частозадаваемых вопросов
    public static final By question1 = By.id("accordion__heading-0");
    public static final By question2 = By.id("accordion__heading-1");
    public static final By question3 = By.id("accordion__heading-2");
    public static final By question4 = By.id("accordion__heading-3");
    public static final By question5 = By.id("accordion__heading-4");
    public static final By question6 = By.id("accordion__heading-5");
    public static final By question7 = By.id("accordion__heading-6");
    public static final By question8 = By.id("accordion__heading-7");


    // Локаторы для выпадающего ответа

    public static  final  By answer1 = By.xpath("//div[@id = 'accordion__panel-0']//p");
    public static  final  By answer2 = By.xpath("//div[@id = 'accordion__panel-1']//p");
    public static  final  By answer3 = By.xpath("//div[@id = 'accordion__panel-2']//p");
    public static  final  By answer4 = By.xpath("//div[@id = 'accordion__panel-3']//p");
    public static  final  By answer5 = By.xpath("//div[@id = 'accordion__panel-4']//p");
    public static  final  By answer6 = By.xpath("//div[@id = 'accordion__panel-5']//p");
    public static  final  By answer7 = By.xpath("//div[@id = 'accordion__panel-6']//p");
    public static  final  By answer8 = By.xpath("//div[@id = 'accordion__panel-7']//p");

    // Локаторы для выпадающих ответов
    public static final String answer1Text = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String answer2Text = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String answer3Text = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String answer4Text = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String answer5Text = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String answer6Text = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String answer7Text = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String answer8Text = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    // Локаторы для кнопок "Заказать"
    public static final By topOrderButton = By.className("Button_Button__ra12g");
    public static final By lowerOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button");

    public void clickQuestion(By question) throws InterruptedException {
        WebElement questionElement = driver.findElement(question);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",questionElement);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(questionElement));
        questionElement.click();
    }

    public String getTest(By answer) {
        WebElement answerElement = driver.findElement(answer);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", answerElement);
        return answerElement.getText();
    }

    public void closeCookie() throws InterruptedException {
      WebElement cookie = driver.findElement(By.xpath("//button[contains(@class,'App_CookieButton')]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",cookie);
      cookie.click();
    }

    public void clickOrderButton(String button) {

        if ( button.contains("верх")){
            WebElement orderButton = driver.findElement(topOrderButton);
            orderButton.click();
        }
        else if (button.contains("ни")) {
            WebElement orderButton = driver.findElement(lowerOrderButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", orderButton);
            orderButton.click();
        }
    }
}
