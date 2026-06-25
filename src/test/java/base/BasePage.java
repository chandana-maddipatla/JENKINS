package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public void click(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            logger.info("Clicked element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click element: {} | Error: {}", locator, e.getMessage());
            throw e;
        }
    }

    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            logger.info("Clicked WebElement");
        } catch (Exception e) {
            logger.error("Failed to click WebElement | Error: {}", e.getMessage());
            throw e;
        }
    }

    public void jsClick(WebElement element) {
        try {
            js.executeScript("arguments[0].click();", element);
            logger.info("JS clicked element");
        } catch (Exception e) {
            logger.error("JS click failed | Error: {}", e.getMessage());
            throw e;
        }
    }



    public void type(By locator, String text) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            el.clear();
            el.sendKeys(text);
            logger.info("Typed '{}' into: {}", text, locator);
        } catch (Exception e) {
            logger.error("Failed to type into: {} | Error: {}", locator, e.getMessage());
            throw e;
        }
    }

    public void typeWithKeys(By locator, String text, Keys key) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            el.clear();
            el.sendKeys(text, key);
            logger.info("Typed '{}' and pressed key {} into: {}", text, key, locator);
        } catch (Exception e) {
            logger.error("Failed to type+key into: {} | Error: {}", locator, e.getMessage());
            throw e;
        }
    }

    // ─── Get Text ─────────────────────────────────────────────────────────────

    public String getText(By locator) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
            logger.info("Got text '{}' from: {}", text, locator);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from: {} | Error: {}", locator, e.getMessage());
            throw e;
        }
    }

    public String getText(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).getText();
        } catch (Exception e) {
            logger.error("Failed to get text from WebElement | Error: {}", e.getMessage());
            throw e;
        }
    }

    // ─── Wait ─────────────────────────────────────────────────────────────────

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ─── Scroll ───────────────────────────────────────────────────────────────

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        logger.info("Scrolled to bottom of page");
    }

    public void scrollBy(int pixels) {
        js.executeScript("window.scrollBy(0," + pixels + ")");
        logger.info("Scrolled by {} pixels", pixels);
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info("Scrolled to element");
    }

    // ─── Hover ────────────────────────────────────────────────────────────────

    public void hoverOver(WebElement element) {
        actions.moveToElement(element).perform();
        logger.info("Hovered over element");
    }

    // ─── Navigation ───────────────────────────────────────────────────────────

    public void navigateTo(String url) {
        driver.get(url);
        logger.info("Navigated to: {}", url);
    }

    public void navigateBack() {
        driver.navigate().back();
        logger.info("Navigated back");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // ─── Attribute ────────────────────────────────────────────────────────────

    public String getAttribute(By locator, String attribute) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(attribute);
    }

    // ─── Find ─────────────────────────────────────────────────────────────────

    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ─── Wait for page load ───────────────────────────────────────────────────

    public void waitForPageLoad() {
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
        logger.info("Page fully loaded");
    }
}
