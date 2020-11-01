package steps;

import actions.Actions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.Base;
import utils.Constants;

public class ActionSteps {
    private static final Logger logger = LoggerFactory.getLogger(ActionSteps.class);
    Actions actions;
    WebDriver webDriver;
    Base base;

    public ActionSteps(Base base) {
        this.base = base;
        actions = new Actions(base.webDriver);
        this.webDriver = base.webDriver;
    }

    @Given("user navigate to url {string}")
    public void navigateUrl(String url) {
        boolean flag = false;
        flag = actions.urlNavigation(url);
        Assert.assertTrue(flag, "Navigation to url failed");
    }

    @And("user click on {string}")
    public void clickOnElement(String elementName) {
        boolean flag = actions.click(elementName);
        Assert.assertTrue(flag, "Click operation failed");
    }

    @Then("user accept product added alert")
    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(webDriver, Constants.MAX_TIMEOUT);
        wait.until(ExpectedConditions.alertIsPresent());
        webDriver.switchTo().alert().accept();
    }


    @Then("user enter {string} as {string}")
    public void clickOnElement(String elementName, String elementValue) {
        boolean flag = actions.enterValue(elementName, elementValue);
        Assert.assertTrue(flag, "Enter value operation failed");

    }


    @And("user wait until {string} is displayed")
    public void waitForElement(String elementName) {
        boolean flag = actions.verifyPresentOfElement(elementName);
        Assert.assertTrue(flag, "expected element not found");
    }



}
