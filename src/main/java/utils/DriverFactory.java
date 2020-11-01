package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public static WebDriver getWebDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--incognito");
            options.addArguments("disable-infobars");
            options.addArguments("--test-type");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-notifications");

            WebDriverManager.chromedriver().browserVersion("84").setup();
            return new ChromeDriver(options);
        }
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        return null;
    }
}
