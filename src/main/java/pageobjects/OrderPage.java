package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage {

    private WebDriver driver;

    // Локаторы для первой страницы заказа
    private static final By name = By.xpath("//input[contains(@placeholder, 'Имя')]");
    private static final By surname = By.xpath("//input[contains(@placeholder, 'Фамилия')]");
    private static final By destinationAddress = By.xpath("//input[contains(@placeholder, 'Адрес')]");
    private static final By metroStation = By.className("select-search__input");
    private static final By telefonNumber = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    private static final By nextButton = By.xpath("//div[@class='Order_NextButton__1_rCA']//button");

    //Локаторы для второй страницы заказа
    private static final By dateToDelliver = By.xpath("//input[contains(@placeholder, 'Когда')]");
    private static final By rentalPeriod = By.className("Dropdown-placeholder");
    private static final By scooterCollorBlackCB = By.xpath("//input[@id = 'black']");
    private static final By scooterCollorGrayCB = By.xpath("//input[@id = 'grey']");
    private static final By commentForCourier = By.xpath("//input[contains(@placeholder, 'Комментарий')]");
    private static final By finishTheOrderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");

    //Локаторы для окна Подтверждения заказа
    private static final By orderConfirmationWindow = By.className("rder_Text__2broi");
    private static final By orderConfirmationYesButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");

    //Локатор для окна "Заказ оформлен"
    private static final By issueWindow = By.className("Order_Modal__YZ-d3");

    //Листы для выбора из списка.
    private By listStation = By.xpath("//div[@class = 'Order_Text__2broi']");
    private By listPeriod = By.xpath("//div[@role = 'option']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    private By selectedMonthDays = By.xpath("//div[contains(@class,'react-datepicker__day')]");

    public void setUserParams(String name, String surname, String destinationAddress, String metroStation,
                              String telefonNumber) {
         setUserName(name);
         setSurname(surname);
         setDestination(destinationAddress);
         setmetroStation(metroStation);
         setTelefonNumber(telefonNumber);
         clickNextButton();
    }

    private void setTelefonNumber(String telefonNumberVelue) {
        WebElement telefonNumberField = driver.findElement(telefonNumber);
        telefonNumberField.sendKeys(telefonNumberVelue);
    }

    private void setmetroStation(String metroStationValue) {
        WebElement metroStationField = driver.findElement(metroStation);
        metroStationField.click();
        List<WebElement> stations = driver.findElements(listStation);
        for (WebElement element : stations){
            if(element.getText().contains(metroStationValue)){
                element.click();
                break;
            }
        }
    }

    private void setDestination(String destinationAddressValue) {
        WebElement destinationAddressField = driver.findElement(destinationAddress);
        destinationAddressField.sendKeys(destinationAddressValue);
    }

    private void setSurname(String surnameValue) {
        WebElement userSurnameField = driver.findElement(surname);
        userSurnameField.sendKeys(surnameValue);
    }

    private void setUserName(String nameValue) {
        WebElement userNameField = driver.findElement(name);
        userNameField.sendKeys(nameValue);
    }

    private void clickNextButton(){
        WebElement button = driver.findElement(nextButton);
        button.click();
    }

    public void setOrderParams(String dateToDelliver, String rentalPeriod, String scooterCollor, String commentForCourier) throws InterruptedException {
        setDateToDelliver(dateToDelliver);
        setRentalPeriod(rentalPeriod);
        setScooterCollor(scooterCollor);
        setCommentForCourier(commentForCourier);
        clickFinishOrderButton();

    }

    private void clickFinishOrderButton() {
        WebElement button = driver.findElement(finishTheOrderButton);
        button.click();
    }

    private void setCommentForCourier(String commentForCourierValue) {
        WebElement comment = driver.findElement(commentForCourier);
        comment.sendKeys(commentForCourierValue);
    }

    private void setScooterCollor(String scooterCollorValue) throws InterruptedException {
        if ( scooterCollorValue.contains("черн")) {
            WebElement collorBlack = driver.findElement(scooterCollorBlackCB);
            collorBlack.click();
        } else if ( scooterCollorValue.contains("сер")){
                WebElement collorGray = driver.findElement(scooterCollorGrayCB);
                Thread.sleep(2000);
                collorGray.click();
        }
    }

    private void setRentalPeriod(String rentalPeriodValue) {
        WebElement rentalField = driver.findElement(rentalPeriod);
        rentalField.click();
        List<WebElement> periods= driver.findElements(listPeriod);
         for ( WebElement element : periods){
             if (element.getText().contains(rentalPeriodValue)){
                 element.click();
                 break;
             }
         }
    }

    private void setDateToDelliver(String dateToDelliverValue) {
        WebElement date = driver.findElement(dateToDelliver);
        date.click();

        // Находим все элементы, которые представляют дни в текущем отображаемом месяце календаря
        List<WebElement> allSelectedMonthDays = driver.findElements(selectedMonthDays);

        // Извлекаем первые два символа из строки даты - это день месяца (например "05" для 5-го числа)
        String day = dateToDelliverValue.substring(0, 2);

        // Проверяем, есть ли ведущий ноль в номере дня и если есть - убираем его
        if (dateToDelliverValue.substring(0,1).equals("0")) {
            day = dateToDelliverValue.substring(1, 2);
        }

        // Перебираем все найденные дни месяца в календаре
        for (WebElement calDay : allSelectedMonthDays) {
            // Сравниваем текст каждого элемента с нужным днем (без ведущего нуля)
            if (calDay.getText().equals(day)) {
                // Если нашли нужный день - кликаем по нему
                calDay.click();
                break;
            }
        }
    }

    public void clickConfirmationButton() {
        WebElement button = driver.findElement(orderConfirmationYesButton);
        button.click();
    }

    public boolean checkConfirmationIsActive() {
        WebElement issue = driver.findElement(issueWindow);
        return  issue.isDisplayed();
    }
}
