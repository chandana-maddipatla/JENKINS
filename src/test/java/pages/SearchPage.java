package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    private static final Logger logger =
            LogManager.getLogger(SearchPage.class);

    // Locators
    private final By searchResults =
            By.cssSelector("div[data-component-type='s-search-result']");

    private final By noResultsMessage =
            By.xpath("//span[contains(text(),'No results')]");

    private final By searchEmptyMessage =
            By.xpath("//span[contains(text(),'Try different keywords')]");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getAllSearchResults() {

        waitForPageLoad();

        List<WebElement> results =
                findAll(searchResults);

        logger.info("Found {} search results",
                results.size());

        return results;
    }

    public WebElement getLastSearchResult() {

        List<WebElement> results =
                getAllSearchResults();

        if (results.isEmpty()) {

            throw new RuntimeException(
                    "No search results found"
            );
        }

        // Scroll to load all products
        scrollToBottom();

        waitForPageLoad();

        results =
                getAllSearchResults();

        WebElement last =
                results.get(results.size() - 1);

        scrollToElement(last);

        logger.info("Got last search result");

        return last;
    }

    public String getLastItemTitle() {

        try {

            WebElement lastItem =
                    getLastSearchResult();

            // safer locator
            WebElement titleEl =
                    lastItem.findElement(
                            By.xpath(".//h2//span")
                    );

            String title =
                    titleEl.getText();

            logger.info("Last item title {}",
                    title);

            return title;

        } catch (Exception e) {

            logger.error(
                    "Failed getting title {}",
                    e.getMessage()
            );

            return "Title not found";
        }
    }

    public String getLastItemPrice() {

        try {

            WebElement lastItem =
                    getLastSearchResult();

            WebElement priceEl =
                    lastItem.findElement(
                            By.cssSelector(".a-price .a-offscreen")
                    );

            String price =
                    priceEl.getText();

            logger.info("Last item price {}",
                    price);

            return price;

        } catch (Exception e) {

            logger.error(
                    "Price not found {}",
                    e.getMessage()
            );

            return "Price not available";
        }
    }

    public boolean isNoResultsDisplayed() {

        try {

            return isDisplayed(noResultsMessage)
                    || isDisplayed(searchEmptyMessage);

        } catch (Exception e) {

            return false;
        }
    }

    public boolean hasSearchResults() {

        return !findAll(searchResults).isEmpty();
    }

    public int getResultCount() {

        return findAll(searchResults).size();
    }
}