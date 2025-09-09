package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features", // runs all feature files in this folder
    glue = {"stepdefinitions","hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber.json"
    },
    monochrome = true
   
)
public class TestRunner_CNG extends AbstractTestNGCucumberTests {
   
}
