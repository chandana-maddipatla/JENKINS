@Regression @Navigation
Feature: Amazon Left Navigation

  Background:
    Given user opens amazon website

  @Positive
  Scenario: Open left navigation menu
    When user opens the left navigation menu
    Then the left nav menu should have items

  @Positive
  Scenario: Navigate to Mobile Phones via left nav menu
    When user navigates to Mobiles and then Mobile Phones
    Then the page title should contain Amazon

  @Positive
  Scenario: Navigate to menu item and return to homepage
    When user opens the left navigation menu
    And user selects "Mobiles" from the menu
    And user navigates back to the main menu
    Then user should be on the homepage

  @Negative
  Scenario: Verify homepage is Amazon after navigation
    When user opens the left navigation menu
    And user navigates back to the main menu
    Then user should be on the homepage