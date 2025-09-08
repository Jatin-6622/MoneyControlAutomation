package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;

import reports.ExtentManager;

public class ScreenShotUtils {

    public static void captureScreenshot(WebDriver driver, String stepName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            byte[] fileContent = Files.readAllBytes(srcFile.toPath());
            String base64Image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            ExtentManager.getTest().info(stepName,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());

            LoggerUtil.info("Screenshot captured for step: " + stepName);
        } catch (IOException e) {
            LoggerUtil.error("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
