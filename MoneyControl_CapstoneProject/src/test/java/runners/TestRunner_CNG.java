package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
	    features = "src/test/resources/features",   // only login feature
	    glue = {"stepdefinitions","hooks"},
	    plugin = {
	        "pretty",
	        "html:target/cucumber-reports.html",
	        "json:target/cucumber.json"
	    },
	    monochrome = true,
	    tags = "@positive"   // optional: run only positive tests
	)
public class TestRunner_CNG extends AbstractTestNGCucumberTests {
	}

//# Cucumber TestNG runner