package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);
    private static final String SCREENSHOT_DIR = "target/screenshots/";

    public static byte[] captureAsBytes(WebDriver driver) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            logger.info("Screenshot captured as bytes");
            return screenshot;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
            return new byte[0];
        }
    }

    public static String captureToFile(WebDriver driver, String scenarioName) {
        try {
            // Create directory if not exists
            Path dir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            // Generate unique filename
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String safeName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");
            String fileName = SCREENSHOT_DIR + safeName + "_" + timestamp + ".png";

            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get(fileName));

            logger.info("Screenshot saved to: {}", fileName);
            return fileName;

        } catch (IOException e) {
            logger.error("Failed to save screenshot to file: {}", e.getMessage());
            return null;
        }
    }
}
