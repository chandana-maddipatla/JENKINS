package hooks;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        LoggerUtil.scenarioStart(scenario.getName());
        logger.info("Tags: {}", scenario.getSourceTagNames());
        DriverFactory.initDriver();
        logger.info("Browser launched for scenario: {}", scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                logger.error("Scenario FAILED: {}", scenario.getName());
                byte[] screenshot = ScreenshotUtil.captureAsBytes(DriverFactory.getDriver());
                if (screenshot.length > 0) {
                    scenario.attach(screenshot, "image/png", "Failure Screenshot");
                    logger.info("Screenshot attached to report");
                }

                String filePath = ScreenshotUtil.captureToFile(
                    DriverFactory.getDriver(),
                    scenario.getName()
                );
                if (filePath != null) {
                    logger.info("Screenshot saved: {}", filePath);
                }
            } else {
                logger.info("Scenario PASSED: {}", scenario.getName());
            }
        } catch (Exception e) {
            logger.error("Error in teardown: {}", e.getMessage());
        } finally {
            DriverFactory.quitDriver();
            logger.info("Browser closed");
            LoggerUtil.scenarioEnd(scenario.getName(), !scenario.isFailed());
        }
    }
}
