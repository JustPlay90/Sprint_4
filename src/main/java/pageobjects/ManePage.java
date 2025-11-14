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

    // Локаторы для кнопок "Заказать"
    private static final By topOrderButton = By.className("Button_Button__ra12g");
    private static final By lowerOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button");

    public void clickQuestion(int num){
        By question = By.id("accordion__heading-" + num);
        WebElement questionElement = driver.findElement(question);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",questionElement);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(questionElement));
        questionElement.click();
    }

    public String getText(int num) {
        By answer = By.xpath("//div[@id = 'accordion__panel-"+num+"']//p");
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
