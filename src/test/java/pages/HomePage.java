package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By searchBox =
            By.id("twotabsearchtextbox");

    private final By searchButton =
            By.id("nav-search-submit-button");

    private final By todaysDealsLink =
            By.xpath("//a[contains(@href,'deals')]");

    private final By hamburgerMenu =
            By.id("nav-hamburger-menu");

    private final By logoLink =
            By.id("nav-logo");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openAmazon() {
        navigateTo("https://www.amazon.in");
        waitForPageLoad();
    }

    public void searchFor(String product) {

        waitForVisible(searchBox);

        type(searchBox, product);

        click(searchButton);

        waitForPageLoad();
    }

    public void clickTodaysDeals() {
        click(todaysDealsLink);
        waitForPageLoad();
    }

    public void clickHamburgerMenu() {
        click(hamburgerMenu);
    }

    public void clickLogoToGoHome() {
        click(logoLink);
        waitForPageLoad();
    }

    public boolean isSearchBoxDisplayed() {
        return isDisplayed(searchBox);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
