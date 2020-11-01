package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {
    private WebDriverWait wait;

    public GenericMethods(WebDriver webDriver) {
        wait = new WebDriverWait(webDriver, Constants.MAX_TIMEOUT);
    }


    public WebElement getClickableElement(String elementName) {
        String locator = PropertiesHelper.getProperty(elementName);
        return wait.until(ExpectedConditions.elementToBeClickable(this.getSelector(locator)));
    }

    public WebElement presentOfElement(String elementName) {
        String locator = PropertiesHelper.getProperty(elementName);
        return wait.until(ExpectedConditions.presenceOfElementLocated(this.getSelector(locator)));
    }


    public By getSelector(String loc) {
        String findBy = loc.split("\\.")[0];
        String locator = loc.split("\\.")[1];

        if (findBy.equalsIgnoreCase("id"))
            return By.id(locator);
        else if (findBy.equalsIgnoreCase("xpath"))
            return By.xpath(locator);

        return null;
    }

    public static void sleep(int sec){
        try {
            Thread.sleep(sec * 1000);
        }catch (InterruptedException e){
        }
    }

}
