package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Object... args) {
        logger.info(message, args);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void stepStart(String stepName) {
        logger.info("▶ STEP: {}", stepName);
    }

    public static void stepPass(String stepName) {
        logger.info("✓ PASS: {}", stepName);
    }

    public static void stepFail(String stepName, String reason) {
        logger.error("✗ FAIL: {} | Reason: {}", stepName, reason);
    }

    public static void scenarioStart(String scenarioName) {
        logger.info("=================================================");
        logger.info("SCENARIO START: {}", scenarioName);
        logger.info("=================================================");
    }

    public static void scenarioEnd(String scenarioName, boolean passed) {
        if (passed) {
            logger.info("SCENARIO PASSED: {}", scenarioName);
        } else {
            logger.error("SCENARIO FAILED: {}", scenarioName);
        }
        logger.info("=================================================");
    }
}
