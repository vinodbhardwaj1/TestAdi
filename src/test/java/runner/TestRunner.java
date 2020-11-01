package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/cucumber_html.html", "json:target/cucumber/cucumber_json.json"},
        features = "src/test/resources/features",
        glue = {"steps"},
        dryRun = false
)

public class TestRunner {

}
