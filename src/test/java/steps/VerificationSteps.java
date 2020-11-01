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
import utils.GenericMethods;

public class VerificationSteps {
    private static final Logger logger = LoggerFactory.getLogger(VerificationSteps.class);
    Actions actions;
    WebDriver webDriver;
    Base base;

    public VerificationSteps(Base base) {
        this.base = base;
        actions = new Actions(base.webDriver);
        this.webDriver = base.webDriver;
    }


    @Then("user store value of {string}")
    public void storeElementValue(String elementName) {
        GenericMethods.sleep(10);
        String value = actions.getElementValue(elementName);
        logger.info(elementName+": "+value);
        base.DataStore.put(elementName, value);
    }


    @Then("user capture purchase id and amount")
    public void getPurchaseIdAndAmount() {
        String value = actions.getElementValue("PurchaseSummary");
        String id = value.split("\n")[0];
        String amount = value.split("\n")[1];
        logger.info("Purchase Id: " + id);
        logger.info("Purchase Amount: " + amount);
        Assert.assertNotNull(id);
    }

    @Then("user verify purchase amount")
    public void verifyPurchaseAmount() {
        String expectedAmount = base.DataStore.get("TotalPrice").toString();
        String value = actions.getElementValue("PurchaseSummary");
        String amount = value.split("\n")[1].split("\\s")[1];
        logger.info("Actual Amount: " + amount);
        logger.info("Expected Amount: " + expectedAmount);
        Assert.assertEquals(amount, expectedAmount);
    }


}
