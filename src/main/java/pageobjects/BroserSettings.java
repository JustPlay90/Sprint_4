package pageobjects;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BroserSettings {
    public ChromeDriver testChromeBrowser(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized", "--no-sandbox","--disable-dev-shm-usage");
         return new ChromeDriver(chromeOptions);
    }

    public FirefoxDriver testFireFoxBrowser(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--kiosk","--no-sandbox","--disable-dev-shm-usage");
        return new FirefoxDriver(firefoxOptions);
    }
}
