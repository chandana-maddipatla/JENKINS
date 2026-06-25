package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.DealsPage;
import pages.HomePage;
import utils.LoggerUtil;

public class DealsSteps {

    HomePage homePage =
            new HomePage(DriverFactory.getDriver());

    DealsPage dealsPage =
            new DealsPage(DriverFactory.getDriver());

    @Given("user opens amazon website")
    public void user_opens_amazon_website() {

        LoggerUtil.stepStart("Opening Amazon website");

        homePage.openAmazon();

        String url =
                DriverFactory.getDriver().getCurrentUrl();

        Assertions.assertTrue(
                url.contains("amazon"),
                "Amazon page should be open"
        );

        LoggerUtil.stepPass("Amazon opened successfully");
    }

    @When("user clicks Today's Deals")
    public void user_clicks_todays_deals() {

        LoggerUtil.stepStart("Click Today's Deals");

        homePage.clickTodaysDeals();

        LoggerUtil.stepPass("Clicked Today's Deals");
    }

    @When("user selects third deal")
    public void user_selects_third_deal() {

        dealsPage.clickNthDeal(3);
    }

    @When("user adds item to cart")
    public void user_adds_item_to_cart() {

        dealsPage.addToCart();
    }

    @Then("cart quantity should be {int}")
    public void cart_quantity_should_be(Integer expected) {

        int actual = dealsPage.getCartCount();

        Assertions.assertEquals(expected, actual);
    }

    // Missing step definitions

    @Then("deals page should load successfully")
    public void deals_page_should_load_successfully() {

        String url = DriverFactory.getDriver().getCurrentUrl();

        Assertions.assertTrue(
                url.contains("deal")
                        || url.contains("goldbox"),
                "Deals page not loaded"
        );
    }

    @When("user selects the {int} deal")
    public void user_selects_the_deal(Integer index) {

        dealsPage.clickNthDeal(index);
    }

    @When("user adds the item to cart")
    public void user_adds_the_item_to_cart() {

        dealsPage.addToCart();
    }

    @Then("cart count should increase by {int}")
    public void cart_count_should_increase_by(Integer expected) {

        int actual = dealsPage.getCartCount();

        Assertions.assertEquals(expected, actual);
    }

    @Then("add to cart button should be present on deal product page")
    public void add_to_cart_button_should_be_present_on_deal_product_page() {

        Assertions.assertTrue(
                dealsPage.isAddToCartButtonPresent(),
                "Add to Cart button not visible"
        );
    }
}