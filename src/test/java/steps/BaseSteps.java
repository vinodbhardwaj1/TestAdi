package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.Base;
import utils.DriverFactory;

public class BaseSteps {

    private static final Logger logger = LoggerFactory.getLogger(BaseSteps.class);
    Base base;

    public BaseSteps(Base base) {
        this.base = base;
    }

    @Given("user launch the browser {string}")
    public void launchBrowser(String browser){
        boolean flag = false;
        base.webDriver = DriverFactory.getWebDriver(browser);
        logger.info("WebDriver: " + base.webDriver);
        if (base.webDriver != null) {
            flag = true;
        }
        Assert.assertTrue(flag, browser + " browser launch failed");
    }


}
