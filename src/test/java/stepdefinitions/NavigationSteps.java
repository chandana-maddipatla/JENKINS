package stepdefinitions;

import base.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.NavigationPage;
import utils.LoggerUtil;

public class NavigationSteps {

    private NavigationPage navigationPage;

    @When("user opens the left navigation menu")
    public void user_opens_left_navigation_menu() {

        LoggerUtil.stepStart("Open left navigation menu");

        try {

            navigationPage =
                    new NavigationPage(
                            DriverFactory.getDriver()
                    );

            navigationPage.openLeftNav();

            LoggerUtil.stepPass(
                    "Left nav menu opened"
            );

        } catch (Exception e) {

            LoggerUtil.stepFail(
                    "Open left nav",
                    e.getMessage()
            );

            throw e;
        }
    }

    @When("user selects {string} from the menu")
    public void user_selects_from_menu(String menuItem) {

        LoggerUtil.stepStart(
                "Select menu item: " + menuItem
        );

        try {

            navigationPage =
                    new NavigationPage(
                            DriverFactory.getDriver()
                    );

            navigationPage
                    .selectMenuItemByText(menuItem);

            LoggerUtil.stepPass(
                    "Menu item selected: " + menuItem
            );

        } catch (Exception e) {

            LoggerUtil.stepFail(
                    "Select menu item: " + menuItem,
                    e.getMessage()
            );

            throw e;
        }
    }

    @When("user navigates to Mobiles and then Mobile Phones")
    public void user_navigates_to_mobiles_and_phones() {

        LoggerUtil.stepStart(
                "Navigate to Mobile Phones"
        );

        try {

            navigationPage =
                    new NavigationPage(
                            DriverFactory.getDriver()
                    );

            navigationPage.openLeftNav();

            navigationPage
                    .selectMenuItemByText("Mobile");

            navigationPage
                    .selectMenuItemByText("Phone");

            LoggerUtil.stepPass(
                    "Navigated to Mobile Phones"
            );

        } catch (Exception e) {

            LoggerUtil.stepFail(
                    "Navigate to Mobile Phones",
                    e.getMessage()
            );

            throw e;
        }
    }

    @When("user navigates back to the main menu")
    public void user_navigates_back_to_main_menu() {

        LoggerUtil.stepStart(
                "Navigate back to main menu"
        );

        try {

            navigationPage =
                    new NavigationPage(
                            DriverFactory.getDriver()
                    );

            // closes overlay menu
            navigationPage.navigateBack();

            LoggerUtil.stepPass(
                    "Returned to main menu"
            );

        } catch (Exception e) {

            LoggerUtil.stepFail(
                    "Navigate back",
                    e.getMessage()
            );

            throw e;
        }
    }

    @Then("user should be on the homepage")
    public void user_should_be_on_homepage() {

        LoggerUtil.stepStart(
                "Verify homepage"
        );

        navigationPage =
                new NavigationPage(
                        DriverFactory.getDriver()
                );

        String currentUrl =
                DriverFactory
                        .getDriver()
                        .getCurrentUrl();

        System.out.println(
                "Current URL = " + currentUrl
        );

        boolean onHome =
                navigationPage.isOnHomePage();

        Assertions.assertTrue(
                onHome,
                "User should be on Amazon homepage"
        );

        LoggerUtil.stepPass(
                "Verified homepage"
        );
    }

    @Then("the page title should contain Amazon")
    public void page_title_contains_amazon() {

        String title =
                DriverFactory
                        .getDriver()
                        .getTitle();

        System.out.println(
                "Page Title = " + title
        );

        Assertions.assertTrue(
                title.toLowerCase()
                        .contains("amazon"),
                "Page title should contain Amazon. Actual: "
                        + title
        );
    }

    @Then("the left nav menu should have items")
    public void left_nav_should_have_items() {

        navigationPage =
                new NavigationPage(
                        DriverFactory.getDriver()
                );

        int count =
                navigationPage
                        .getAllNavItems()
                        .size();

        System.out.println(
                "Nav item count = " + count
        );

        Assertions.assertTrue(
                count > 0,
                "Left nav should have menu items"
        );

        LoggerUtil.info(
                "Nav menu has {} items",
                count
        );
    }
}