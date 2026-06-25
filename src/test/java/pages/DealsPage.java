package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DealsPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(DealsPage.class);

    // Updated safer locators
    private final By dealItems =
            By.xpath("//a[contains(@href,'/deals') or contains(@href,'/gp')]");

    private final By dealItemsAlt =
            By.xpath("//div[contains(@class,'a-section')]");

    private final By addToCartBtn = By.id("add-to-cart-button");
    private final By cartCountBadge = By.id("nav-cart-count");
    private final By productTitle = By.id("productTitle");

    public DealsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getAllDeals() {
        try {
            List<WebElement> deals = findAll(dealItems);

            if (deals.isEmpty()) {
                deals = findAll(dealItemsAlt);
            }

            logger.info("Found {} deal items", deals.size());
            logger.info("Current URL: " + driver.getCurrentUrl());

            return deals;

        } catch (Exception e) {
            logger.error("Error fetching deals: {}", e.getMessage());
            throw e;
        }
    }

    public void clickNthDeal(int index) {
        try {
            List<WebElement> deals = getAllDeals();

            if (deals.size() < index) {
                throw new RuntimeException(
                        "Not enough deals. Found: "
                                + deals.size()
                                + ", requested index: "
                                + index
                );
            }

            WebElement deal = deals.get(index - 1);

            scrollToElement(deal);
            jsClick(deal);
            waitForPageLoad();

            logger.info("Clicked deal at index {}", index);

        } catch (Exception e) {
            logger.error("Failed to click deal at index {}: {}", index, e.getMessage());
            throw e;
        }
    }

    public void addToCart() {
        try {
            click(addToCartBtn);
            logger.info("Clicked Add to Cart");

        } catch (Exception e) {
            logger.error("Add to Cart failed: {}", e.getMessage());
            throw e;
        }
    }

    public int getCartCount() {
        try {
            String countText = getText(cartCountBadge).trim();

            int count = Integer.parseInt(countText);

            logger.info("Cart count: {}", count);

            return count;

        } catch (Exception e) {
            logger.error("Failed to get cart count: {}", e.getMessage());
            return 0;
        }
    }

    public String getProductTitle() {
        return getText(productTitle);
    }

    public boolean isAddToCartButtonPresent() {
        return isDisplayed(addToCartBtn);
    }
}