package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import pages.SearchPage;
import utils.LoggerUtil;

public class SearchSteps {

    private HomePage homePage;
    private SearchPage searchPage;
    private String lastItemTitle;
    private String lastItemPrice;

    @When("user searches for product {string}")
    public void user_searches_for_product(String product) {
        LoggerUtil.stepStart("Search for product: " + product);
        try {
            homePage = new HomePage(DriverFactory.getDriver());
            homePage.searchFor(product);
            searchPage = new SearchPage(DriverFactory.getDriver());
            LoggerUtil.stepPass("Search performed for: " + product);
        } catch (Exception e) {
            LoggerUtil.stepFail("Search", e.getMessage());
            throw e;
        }
    }

    @When("user scrolls to the last displayed item")
    public void user_scrolls_to_last_displayed_item() {
        LoggerUtil.stepStart("Scroll to last displayed item");
        try {
            searchPage = new SearchPage(DriverFactory.getDriver());
            lastItemTitle = searchPage.getLastItemTitle();
            lastItemPrice = searchPage.getLastItemPrice();
            LoggerUtil.info("Last item title: {}", lastItemTitle);
            LoggerUtil.info("Last item price: {}", lastItemPrice);
            LoggerUtil.stepPass("Scrolled to last item");
        } catch (Exception e) {
            LoggerUtil.stepFail("Scroll to last item", e.getMessage());
            throw e;
        }
    }

    @Then("last item details should be displayed")
    public void last_item_details_should_be_displayed() {
        LoggerUtil.stepStart("Verify last item details");
        Assertions.assertNotNull(lastItemTitle, "Last item title should not be null");
        Assertions.assertFalse(
            lastItemTitle.isEmpty(),
            "Last item title should not be empty"
        );
        LoggerUtil.info("Verified last item: '{}'", lastItemTitle);
        LoggerUtil.stepPass("Last item details verified");
    }

    @Then("search results should be displayed")
    public void search_results_should_be_displayed() {
        LoggerUtil.stepStart("Verify search results present");
        searchPage = new SearchPage(DriverFactory.getDriver());
        boolean hasResults = searchPage.hasSearchResults();
        Assertions.assertTrue(hasResults, "Search results should be present");
        int count = searchPage.getResultCount();
        LoggerUtil.info("Search returned {} results", count);
        LoggerUtil.stepPass("Search results verified: " + count + " items");
    }

    @Then("no results message should be displayed")
    public void no_results_message_should_be_displayed() {
        LoggerUtil.stepStart("Verify no results message");
        searchPage = new SearchPage(DriverFactory.getDriver());
        boolean noResults = searchPage.isNoResultsDisplayed();
        Assertions.assertTrue(
            noResults || !searchPage.hasSearchResults(),
            "No results message should appear for invalid search"
        );
        LoggerUtil.stepPass("No results state verified");
    }

    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitle) {
        String actualTitle = DriverFactory.getDriver().getTitle();
        Assertions.assertTrue(
            actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()),
            "Page title should contain: " + expectedTitle + " but was: " + actualTitle
        );
    }
}
