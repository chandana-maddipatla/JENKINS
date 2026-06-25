# Amazon BDD Automation Framework

A production-quality BDD test automation framework for Amazon.in using Cucumber, Selenium, and JUnit 5.

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java 11 | Core language |
| Selenium 4 | Browser automation |
| Cucumber 7 | BDD framework |
| JUnit 5 | Test runner |
| WebDriverManager | Driver management |
| Log4j2 | Logging |
| Masterthought | HTML reports |
| GitHub Actions | CI/CD |

## Project Structure

```
amazon-bdd-framework/
├── src/test/java/
│   ├── base/
│   │   ├── BasePage.java          # Reusable selenium actions
│   │   └── DriverFactory.java     # Thread-safe WebDriver management
│   ├── pages/
│   │   ├── HomePage.java
│   │   ├── DealsPage.java
│   │   ├── SearchPage.java
│   │   └── NavigationPage.java
│   ├── stepdefinitions/
│   │   ├── DealsSteps.java
│   │   ├── SearchSteps.java
│   │   └── NavigationSteps.java
│   ├── hooks/
│   │   └── Hooks.java             # Before/After with screenshot on failure
│   ├── utils/
│   │   ├── ScreenshotUtil.java
│   │   ├── LoggerUtil.java
│   │   └── WaitUtils.java
│   └── runner/
│       └── TestRunner.java
└── src/test/resources/
    ├── features/
    │   ├── deals.feature
    │   ├── search.feature
    │   └── navigation.feature
    └── log4j2.xml
```

## How to Run

### Prerequisites
- Java 11+
- Maven 3.8+
- Chrome browser installed

### Run all tests
```bash
mvn test
```

### Run specific tag
```bash
mvn test -Dcucumber.filter.tags="@Deals"
mvn test -Dcucumber.filter.tags="@Search"
mvn test -Dcucumber.filter.tags="@Navigation"
mvn test -Dcucumber.filter.tags="@Positive"
```

### Generate HTML report
```bash
mvn verify
```

Report will be at: `target/cucumber-html-reports/overview-features.html`

## Key Features

- **Page Object Model** — All UI interactions encapsulated in page classes
- **Reusable BasePage** — Common Selenium methods with built-in explicit waits
- **Screenshot on Failure** — Auto-captured and attached to Cucumber report
- **Log4j2 Logging** — Logs to console + rolling file at `target/logs/automation.log`
- **Dynamic waits** — No Thread.sleep(); uses ExpectedConditions throughout
- **Real user interactions** — Actions class for hover, keyboard; JS for scrolling
- **Positive + Negative + Edge cases** — All three types in every feature
- **Scenario Outline** — Parameterized search tests
- **CI/CD ready** — GitHub Actions workflow included

## Scenarios Covered

### Deals (No Login Required)
- Navigate to Today's Deals
- Select 3rd deal and add to cart
- Verify cart count increases

### Search (No Login Required)
- Search Mobiles and verify results
- Scroll to last item and capture details
- Parameterized search (Mobiles, iPhone, Samsung)
- Invalid search negative case

### Navigation (No Login Required)
- Open left hamburger menu
- Navigate Mobiles → Mobile Phones
- Navigate back to homepage
