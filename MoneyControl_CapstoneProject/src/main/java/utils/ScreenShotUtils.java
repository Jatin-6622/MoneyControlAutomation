package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import reports.ExtentManager;

public class ScreenShotUtils {

    public static void captureScreenshot(WebDriver driver, String stepName) {
        try {
            // 🔹 Check for alert first
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                LoggerUtil.warn("Alert present: " + alertText);

                // Log alert instead of screenshot
                ExtentManager.getTest().log(Status.WARNING, "⚠️ Alert detected: **" + alertText + "**");
                return;
            } catch (NoAlertPresentException e) {
                // continue with screenshot
            }

            // 🔹 Normal screenshot
            String base64Image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            ExtentManager.getTest().log(Status.INFO,
                    "📸 Screenshot for step: **" + stepName + "**",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());

            LoggerUtil.info("Screenshot captured for step: " + stepName);

        } catch (Exception e) {
            LoggerUtil.error("Unexpected error during screenshot: " + e.getMessage());
        }
    }
}
