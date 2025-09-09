package hooks;

import base.BaseSetup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;

import reports.ExtentManager;
import utils.LoggerUtil;

public class ApplicationHooks extends BaseSetup {

    @BeforeAll
    public static void beforeAll() {
        ExtentManager.getExtentReports();
        LoggerUtil.info("===== Test Execution Started =====");
    }

    @Before
    public void launchBrowser(Scenario scenario) {
        setUp();
        ExtentTest test = ExtentManager.getExtentReports()
                .createTest("Scenario: " + scenario.getName())
                .assignCategory("Cucumber Tests")
                .assignAuthor("Jatin Sharma ✅");
        ExtentManager.setTest(test);

        LoggerUtil.info("Starting scenario: " + scenario.getName());
        ExtentManager.getTest().info("🚀 Starting scenario: **" + scenario.getName() + "**");
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = BaseSetup.getDriver();

        if (scenario.isFailed()) {
            try {
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

                ExtentManager.getTest().log(Status.FAIL,
                        "❌ Step failed in scenario: **" + scenario.getName() + "**",
                        com.aventstack.extentreports.MediaEntityBuilder
                                .createScreenCaptureFromBase64String(base64Screenshot)
                                .build());

                scenario.attach(base64Screenshot.getBytes(), "image/png", "Failure Screenshot");
            } catch (Exception e) {
                LoggerUtil.error("Error capturing screenshot: " + e.getMessage());
            }
        } else {
            ExtentManager.getTest().log(Status.PASS, "✅ Step passed successfully");
        }
    }

    @After
    public void quitBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerUtil.error("Scenario failed: " + scenario.getName());
            ExtentManager.getTest().log(Status.FAIL, "❌ Scenario failed: **" + scenario.getName() + "**");
        } else {
            LoggerUtil.info("Scenario passed: " + scenario.getName());
            ExtentManager.getTest().log(Status.PASS, "🎉 Scenario passed: **" + scenario.getName() + "**");
        }
        tearDown();
    }

    @AfterAll
    public static void afterAll() {
        ExtentManager.getExtentReports().flush();
        LoggerUtil.info("===== Test Execution Finished =====");
    }
}
