package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationPage extends BasePage {

    private static final Logger logger =
            LogManager.getLogger(NavigationPage.class);


    private final By hamburgerMenu =
            By.id("nav-hamburger-menu");

    private final By sideNavItems =
            By.xpath("//div[contains(@class,'hmenu-visible')]//a");

    private final By closeMenuBtn =
            By.xpath("//a[@aria-label='Close menu'] | //i[contains(@class,'hmenu-arrow-prev')]");

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public void openLeftNav() {

        try {


            List<WebElement> menuOpen =
                    findAll(
                            By.xpath(
                                    "//div[contains(@class,'hmenu-visible')]"
                            )
                    );


            if (menuOpen.isEmpty()) {

                click(hamburgerMenu);

                waitForVisible(
                        By.xpath(
                                "//div[contains(@class,'hmenu-visible')]"
                        )
                );
            }

            logger.info("Left nav menu opened");

        } catch (Exception e) {

            logger.error(
                    "Failed opening nav: {}",
                    e.getMessage()
            );

            throw e;
        }
    }


    public void selectMenuItemByText(String menuText) {

        try {

            List<WebElement> menuItems =
                    findAll(sideNavItems);

            boolean found = false;

            for (WebElement item : menuItems) {

                String text =
                        item.getText().trim();


                if (text.length() == 0)
                    continue;

                System.out.println("Menu item = " + text);


                if (text.toLowerCase()
                        .contains(menuText.toLowerCase())) {

                    scrollToElement(item);

                    jsClick(item);

                    found = true;

                    logger.info(
                            "Clicked menu item {}",
                            text
                    );

                    break;
                }
            }


            if (!found) {

                logger.warn(
                        "Menu item not found: {}",
                        menuText
                );

                return;
            }

            waitForPageLoad();

        } catch (Exception e) {

            logger.error(
                    "Failed selecting menu {} : {}",
                    menuText,
                    e.getMessage()
            );

            throw e;
        }
    }


    public void navigateBack() {

        try {

            List<WebElement> closeButtons =
                    findAll(closeMenuBtn);

            if (!closeButtons.isEmpty()) {

                jsClick(closeButtons.get(0));

                logger.info("Menu closed");
            }

        } catch (Exception e) {

            logger.error(
                    "Unable to close menu: {}",
                    e.getMessage()
            );
        }
    }

    public boolean isOnHomePage() {

        try {

            String url =
                    driver.getCurrentUrl();

            System.out.println("Current URL = " + url);

            return url.contains("amazon");

        } catch (Exception e) {

            return false;
        }
    }

    public List<WebElement> getAllNavItems() {

        return findAll(sideNavItems);
    }
}