package hooks;

import base.BaseSetup;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;

import reports.ExtentManager;
import utils.LoggerUtil;
import utils.ScreenShotUtils;

public class ApplicationHooks extends BaseSetup {

    @BeforeAll
    public static void beforeAll() {
        ExtentManager.getExtentReports();
        LoggerUtil.info("===== Test Execution Started =====");
    }

    @Before
    public void launchBrowser(Scenario scenario) {
        setUp();
        ExtentTest test = ExtentManager.getExtentReports().createTest(scenario.getName());
        ExtentManager.setTest(test);
        LoggerUtil.info("Starting scenario: " + scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenShotUtils.captureScreenshot(getDriver(), "Failed Step Screenshot");
            LoggerUtil.error("Step failed in scenario: " + scenario.getName());
        } else {
            ScreenShotUtils.captureScreenshot(getDriver(), "Passed Step Screenshot");
            LoggerUtil.info("Step passed in scenario: " + scenario.getName());
        }
    }

    @After
    public void quitBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerUtil.error("Scenario failed: " + scenario.getName());
        } else {
            LoggerUtil.info("Scenario passed: " + scenario.getName());
        }
        tearDown();
    }

    @AfterAll
    public static void afterAll() {
        ExtentManager.getExtentReports().flush();
        LoggerUtil.info("===== Test Execution Finished =====");
    }
}
