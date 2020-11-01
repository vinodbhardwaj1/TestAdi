package actions;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GenericMethods;

public class Actions {
    private static final Logger logger = LoggerFactory.getLogger(Actions.class);

    WebDriver webDriver;
    GenericMethods genericMethods;

    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
        genericMethods = new GenericMethods(webDriver);
    }


    public boolean urlNavigation(String url) {
        logger.info("Loading url: " + url);
        this.webDriver.get(url);
        return true;
    }

    public boolean click(String elementName) {
        boolean flag = false;
        WebElement element;
        try {
            element = genericMethods.getClickableElement(elementName);
            element.click();
        }catch (StaleElementReferenceException e){
            element = genericMethods.getClickableElement(elementName);
            element.click();
        }catch (Exception e){
            e.printStackTrace();
        }
        flag = true;
        logger.info("Clicked on element: " + elementName);
        GenericMethods.sleep(2);
        return flag;
    }

    public boolean enterValue(String elementName, String value) {
        boolean flag = false;
        WebElement element = genericMethods.getClickableElement(elementName);
        element.sendKeys(value);
        flag = true;
        logger.info("Entered value: " + value + " for: " + elementName);
        return flag;
    }

    public boolean verifyPresentOfElement(String elementName) {
        boolean flag = false;
        WebElement element = genericMethods.presentOfElement(elementName);
        if (element.isDisplayed())
            flag = true;
        logger.info("Clicked on element: " + element);
        return flag;
    }

    public String getElementValue(String elementName) {
        logger.info("Getting value of " + elementName);
        WebElement element = genericMethods.presentOfElement(elementName);
        if (element.isDisplayed())
            return element.getText();
        return null;
    }
}
